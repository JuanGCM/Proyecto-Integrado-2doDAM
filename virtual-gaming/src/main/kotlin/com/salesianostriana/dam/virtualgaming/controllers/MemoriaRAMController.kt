package com.salesianostriana.dam.virtualgaming.controllers

import com.salesianostriana.dam.virtualgaming.dtos.MemoriaRAMDTO
import com.salesianostriana.dam.virtualgaming.dtos.toDto
import com.salesianostriana.dam.virtualgaming.models.MemoriaRAM
import com.salesianostriana.dam.virtualgaming.services.MemoriaRAMService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid


@RestController
@RequestMapping("/memorias")
class MemoriaRAMController {

    @Autowired
    lateinit var memoriaService:MemoriaRAMService

    @GetMapping()
    fun getMemorias()=memoriaService.findAll().map { it.toDto() }

    @PostMapping
    fun createMemoriaRAM(@Valid @RequestBody memoriaNueva: MemoriaRAM,
                      @RequestHeader("Authorization") token:String) : ResponseEntity<MemoriaRAMDTO> {
        return ResponseEntity.status(HttpStatus.CREATED).body(memoriaService.createMemoriaRAM(memoriaNueva,token).toDto())
    }

    @DeleteMapping("/{id}")
    fun deleteMemoria(@PathVariable id: Long) =
            memoriaService.deleteMemoria(id)
}