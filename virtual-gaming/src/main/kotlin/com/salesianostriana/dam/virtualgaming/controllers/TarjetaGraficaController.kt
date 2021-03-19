package com.salesianostriana.dam.virtualgaming.controllers

import com.salesianostriana.dam.virtualgaming.dtos.TarjetaGraficaDTO
import com.salesianostriana.dam.virtualgaming.dtos.toDto
import com.salesianostriana.dam.virtualgaming.models.TarjetaGrafica
import com.salesianostriana.dam.virtualgaming.services.TarjetaGraficaServicio
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/graficas")
class TarjetaGraficaController {

    @Autowired
    lateinit var graficaServicio:TarjetaGraficaServicio

    @GetMapping()
    fun getGraficas()=graficaServicio.findAll().map { it.toDto() }

    @PostMapping
    fun createGrafica(@Valid @RequestBody graficaNueva: TarjetaGrafica,
                         @RequestHeader("Authorization") token:String) : ResponseEntity<TarjetaGraficaDTO> {
        return ResponseEntity.status(HttpStatus.CREATED).body(graficaServicio.createGrafica(graficaNueva,token).toDto())
    }
}