package com.salesianostriana.dam.virtualgaming.services

import com.salesianostriana.dam.virtualgaming.errors.ListEntityNotFoundException
import com.salesianostriana.dam.virtualgaming.models.GeneroJuego
import com.salesianostriana.dam.virtualgaming.repositories.GeneroJuegoRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class GeneroJuegoServicio {

    @Autowired
    lateinit var generoRepo:GeneroJuegoRepository

    fun findAll():List<GeneroJuego> {
        var resultado = generoRepo.findAll()
        if (!resultado.isEmpty()) {
            return resultado
        } else {
            throw ListEntityNotFoundException(GeneroJuego::class.java)
        }
    }
}