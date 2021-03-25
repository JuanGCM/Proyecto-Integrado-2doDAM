package com.salesianostriana.dam.virtualgaming.controllers

import com.salesianostriana.dam.virtualgaming.dtos.toDto
import com.salesianostriana.dam.virtualgaming.services.OrdenadorServicio
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/ordenadores")
class OrdenadorController {

    @Autowired
    lateinit var ordeService:OrdenadorServicio

    @GetMapping()
    fun getOrdenadores(@RequestHeader("Authorization") token:String) =
            ordeService.getMisOrdenadores(token).map { it.toDto() }
}