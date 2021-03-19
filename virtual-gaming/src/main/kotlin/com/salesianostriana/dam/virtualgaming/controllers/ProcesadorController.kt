package com.salesianostriana.dam.virtualgaming.controllers

import com.salesianostriana.dam.virtualgaming.dtos.ListadoVideojuegoDTO
import com.salesianostriana.dam.virtualgaming.dtos.ProcesadorDTO
import com.salesianostriana.dam.virtualgaming.dtos.toDto
import com.salesianostriana.dam.virtualgaming.models.Procesador
import com.salesianostriana.dam.virtualgaming.services.ProcesadorServicio
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/procesadores")
class ProcesadorController {

    @Autowired
    lateinit var procesadorServicio: ProcesadorServicio

    @GetMapping()
    fun getProcesadores()=procesadorServicio.findAll().map { it.toDto() }

    @PostMapping
    fun createProcesador(@Valid @RequestBody procesadorNuevo: Procesador,
                         @RequestHeader("Authorization") token:String) : ResponseEntity<ProcesadorDTO> {
        return ResponseEntity.status(HttpStatus.CREATED).body(procesadorServicio.createProcesador(procesadorNuevo,token).toDto())
    }

    @DeleteMapping("/{id}")
    fun deleteProcesador(@PathVariable id: Long) =
            procesadorServicio.deleteProcesador(id)
}