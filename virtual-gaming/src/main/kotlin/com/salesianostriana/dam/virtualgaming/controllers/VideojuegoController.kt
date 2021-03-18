package com.salesianostriana.dam.virtualgaming.controllers

import com.salesianostriana.dam.virtualgaming.dtos.ListadoVideojuegoDTO
import com.salesianostriana.dam.virtualgaming.dtos.toDto
import com.salesianostriana.dam.virtualgaming.dtos.toSpecificDto
import com.salesianostriana.dam.virtualgaming.models.Videojuego
import com.salesianostriana.dam.virtualgaming.services.VideojuegoServicio
import org.springframework.beans.factory.annotation.Autowired
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
    fun searchVideojuego(
            @RequestParam(name = "plat") plataforma:String) =juegoService.searchVideojuego(plataforma)

    @PostMapping
    fun createVideojuego(@Valid @RequestBody videojuegoNuevo:Videojuego,
                         @RequestHeader("Authorization") token:String) : ResponseEntity<ListadoVideojuegoDTO> =
            juegoService.createVideojuego(videojuegoNuevo,token)

    @GetMapping("/{id}")
    fun getVideojuegoPorId(@PathVariable id:Long) = juegoService.getVideojuegoById(id).toSpecificDto()



}