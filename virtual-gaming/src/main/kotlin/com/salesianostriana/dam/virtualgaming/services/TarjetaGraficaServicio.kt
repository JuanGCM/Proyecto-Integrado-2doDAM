package com.salesianostriana.dam.virtualgaming.services

import com.salesianostriana.dam.virtualgaming.errors.ListEntityNotFoundException
import com.salesianostriana.dam.virtualgaming.models.TarjetaGrafica
import com.salesianostriana.dam.virtualgaming.repositories.TarjetaGraficaRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class TarjetaGraficaServicio {

    @Autowired
    lateinit var graficaRepo: TarjetaGraficaRepository

    fun findAll():List<TarjetaGrafica> {
        var resultado = graficaRepo.findAll()
        if (!resultado.isEmpty()) {
            return resultado
        } else {
            throw ListEntityNotFoundException(TarjetaGrafica::class.java)
        }
    }
}