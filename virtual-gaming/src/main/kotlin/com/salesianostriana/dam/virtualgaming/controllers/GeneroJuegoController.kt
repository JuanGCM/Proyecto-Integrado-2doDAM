package com.salesianostriana.dam.virtualgaming.controllers

import com.salesianostriana.dam.virtualgaming.dtos.GeneroJuegoDTO
import com.salesianostriana.dam.virtualgaming.dtos.toDto
import com.salesianostriana.dam.virtualgaming.models.GeneroJuego
import com.salesianostriana.dam.virtualgaming.services.GeneroJuegoServicio
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/generos")
class GeneroJuegoController {

    @Autowired
    lateinit var generoServicio:GeneroJuegoServicio

    @GetMapping()
    fun getGeneros()=generoServicio.findAll().map { it.toDto() }

    @PostMapping
    fun createGeneroJuego(@Valid @RequestBody generoNuevo: GeneroJuego,
                         @RequestHeader("Authorization") token:String) : ResponseEntity<GeneroJuegoDTO> {
        return ResponseEntity.status(HttpStatus.CREATED).body(generoServicio.createGeneroJuego(generoNuevo,token).toDto())
    }
}