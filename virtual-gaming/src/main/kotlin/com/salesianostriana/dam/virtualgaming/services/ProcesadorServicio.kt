package com.salesianostriana.dam.virtualgaming.services

import com.salesianostriana.dam.virtualgaming.errors.ListEntityNotFoundException
import com.salesianostriana.dam.virtualgaming.models.Procesador
import com.salesianostriana.dam.virtualgaming.repositories.ProcesadorRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ProcesadorServicio {

    @Autowired
    lateinit var procesadorRepo:ProcesadorRepository

    fun findAll():List<Procesador> {
        var resultado = procesadorRepo.findAll()
        if (!resultado.isEmpty()) {
            return resultado
        } else {
            throw ListEntityNotFoundException(Procesador::class.java)
        }
    }
}