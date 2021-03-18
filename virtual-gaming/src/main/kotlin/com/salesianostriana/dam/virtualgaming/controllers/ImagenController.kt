package com.salesianostriana.dam.virtualgaming.controllers

import com.salesianostriana.dam.virtualgaming.dtos.ImagenDTO
import com.salesianostriana.dam.virtualgaming.dtos.NuevaImagenDTO
import com.salesianostriana.dam.virtualgaming.dtos.toImagen
import com.salesianostriana.dam.virtualgaming.dtos.toDto
import com.salesianostriana.dam.virtualgaming.errors.SingleEntityNotFoundException
import com.salesianostriana.dam.virtualgaming.imageupload.ImgurBadRequest
import com.salesianostriana.dam.virtualgaming.imageupload.ImgurImageAttribute
import com.salesianostriana.dam.virtualgaming.imageupload.ImgurService
import com.salesianostriana.dam.virtualgaming.models.ImagenVideojuego
import com.salesianostriana.dam.virtualgaming.models.Videojuego
import com.salesianostriana.dam.virtualgaming.repositories.ImagenRepository
import com.salesianostriana.dam.virtualgaming.repositories.VideojuegoRepository
import com.salesianostriana.dam.virtualgaming.services.ImagenServicio
import com.salesianostriana.dam.virtualgaming.services.VideojuegoServicio
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/videojuego")
class ImagenController(
        private val servicio: ImagenServicio,
        private val juegoServicio: VideojuegoServicio,
        private val imgurServicio: ImgurService,
        private var juegoRepo: VideojuegoRepository,
        private var imagenRepo: ImagenRepository
) {

    @GetMapping("/")
    fun getAll() : List<ImagenDTO> {
        val result = servicio.findAll()
        if (result.isEmpty())
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "No hay registros")
        return result.map { it.toDto() }

    }

    @GetMapping("/{id}")
    fun getOne(@PathVariable id: Long) : ImagenDTO =
            servicio.findById(id).map { it.toDto() }
                    .orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND, "Entidad $id no encontrada") }


    @PostMapping("/")
    fun create(@RequestPart("nuevo") new : NuevaImagenDTO,
               @RequestPart("file") file: MultipartFile) : ResponseEntity<ImagenDTO> {

        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(servicio.save(new.toImagen(), file).toDto())
        } catch ( ex : ImgurBadRequest) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Error en la subida de la imagen")
        }

    }


    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) : ResponseEntity<Void> {
        servicio.deleteById(id)
        return ResponseEntity.noContent().build()
    }


    @PostMapping("/{idV}/img")
    fun addToVivienda(@PathVariable("idV")idV:Long,
                      @RequestPart("file") file: MultipartFile) : ResponseEntity<ImagenDTO> {
        try {
            var videojuego = juegoRepo.findById(idV).orElseThrow {
                SingleEntityNotFoundException(idV.toString(),Videojuego::class.java)
            }
            var imagen = imagenRepo.findByVideojuego(videojuego)
            videojuego.imagen = imagen
            var imag = ImagenVideojuego(videojuego, ImgurImageAttribute("",""))
            var aux = servicio.save(imag,file)
            videojuego.addImagen(aux)
            juegoRepo.save(videojuego)
            return ResponseEntity.status(HttpStatus.CREATED).body(aux.toDto())
        } catch ( ex : ImgurBadRequest) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Error en la subida de la imagen")
        }
    }
}