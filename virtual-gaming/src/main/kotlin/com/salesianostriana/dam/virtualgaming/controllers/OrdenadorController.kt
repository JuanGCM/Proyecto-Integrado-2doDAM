package com.salesianostriana.dam.virtualgaming.controllers

import com.salesianostriana.dam.virtualgaming.dtos.*
import com.salesianostriana.dam.virtualgaming.models.Ordenador
import com.salesianostriana.dam.virtualgaming.models.Videojuego
import com.salesianostriana.dam.virtualgaming.services.OrdenadorServicio
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.validation.Valid

@RestController
@RequestMapping("/ordenadores")
class OrdenadorController {

    @Autowired
    lateinit var ordeService:OrdenadorServicio

    @GetMapping()
    fun getOrdenadores(@RequestHeader("Authorization") token:String) =
            ordeService.getMisOrdenadores(token).map { it.toDto() }
/*
    @PostMapping()
    fun createOrdenador(@Valid @RequestBody ordenador: MiOrdenador,
                        @RequestHeader("Authorization") token:String) : ResponseEntity<MisOrdenadoresDTO> {
        return ResponseEntity.status(HttpStatus.CREATED).body(ordeService.createMiOrdenador(ordenador,token).toDto())
    }
*/
    @PostMapping("/{pc}")
    fun createPC(@PathVariable pc:String,
                        @RequestHeader("Authorization") token:String) : ResponseEntity<MisOrdenadoresDTO> {
        return ResponseEntity.status(HttpStatus.CREATED).body(ordeService.createPC(pc,token).toDto())
    }

    @PutMapping("/{pc}")
    fun modifyOrdenador(@PathVariable pc:String)=
            ordeService.modifyOrdenador(pc)

    @DeleteMapping("/{id}")
    fun deleteOrdenador(@PathVariable id: Long) =
            ordeService.deleteOrdenador(id)
}