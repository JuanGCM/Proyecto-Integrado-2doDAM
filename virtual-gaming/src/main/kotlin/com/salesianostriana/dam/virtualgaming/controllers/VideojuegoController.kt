package com.salesianostriana.dam.virtualgaming.controllers

import com.salesianostriana.dam.virtualgaming.dtos.ListadoVideojuegoDTO
import com.salesianostriana.dam.virtualgaming.dtos.toDto
import com.salesianostriana.dam.virtualgaming.dtos.toSpecificDto
import com.salesianostriana.dam.virtualgaming.models.Videojuego
import com.salesianostriana.dam.virtualgaming.services.VideojuegoServicio
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/videojuegos")
class VideojuegoController {

    @Autowired
    lateinit var juegoService:VideojuegoServicio

    @GetMapping()
    fun getVideojuegos()=juegoService.findAll().map { it.toDto() }

    @RequestMapping("/search")
    fun searchVideojuego(@RequestParam(name = "plat") plataforma:String):ResponseEntity<List<ListadoVideojuegoDTO>> {
        return ResponseEntity.status(HttpStatus.FOUND).body(juegoService.searchVideojuego(plataforma).map { it.toDto() })
    }


    @PostMapping
    fun createVideojuego(@Valid @RequestBody videojuegoNuevo:Videojuego,
                         @RequestHeader("Authorization") token:String) : ResponseEntity<ListadoVideojuegoDTO> {
        return ResponseEntity.status(HttpStatus.CREATED).body(juegoService.createVideojuego(videojuegoNuevo,token).toDto())
    }

    @PutMapping("/{id}")
    fun modifyVideojuego(@PathVariable id: Long,@Valid @RequestBody videojuegoNuevo: Videojuego)=
            juegoService.modifyVideojuego(id, videojuegoNuevo)


    @GetMapping("/{id}")
    fun getVideojuegoPorId(@PathVariable id:Long) = juegoService.getVideojuegoById(id).toSpecificDto()


    @DeleteMapping("/{id}")
    fun deleteVideojuego(@PathVariable id: Long) =
            juegoService.deleteVideojuego(id)

}