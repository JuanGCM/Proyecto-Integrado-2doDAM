package com.salesianostriana.dam.virtualgaming.services

import com.salesianostriana.dam.virtualgaming.dtos.ListadoVideojuegoDTO
import com.salesianostriana.dam.virtualgaming.dtos.TarjetaGraficaDTO
import com.salesianostriana.dam.virtualgaming.dtos.toDto
import com.salesianostriana.dam.virtualgaming.errors.ListEntityNotFoundException
import com.salesianostriana.dam.virtualgaming.errors.SingleEntityNotFoundException
import com.salesianostriana.dam.virtualgaming.models.TarjetaGrafica
import com.salesianostriana.dam.virtualgaming.models.Usuario
import com.salesianostriana.dam.virtualgaming.repositories.TarjetaGraficaRepository
import com.salesianostriana.dam.virtualgaming.repositories.UsuarioRepository
import com.salesianostriana.dam.virtualgaming.security.JwtTokenProvider
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class TarjetaGraficaServicio {

    @Autowired
    lateinit var graficaRepo: TarjetaGraficaRepository

    @Autowired
    lateinit var usuRepo:UsuarioRepository

    @Autowired
    lateinit var jwt: JwtTokenProvider


    fun findAll():List<TarjetaGrafica> {
        var resultado = graficaRepo.findAll()
        if (!resultado.isEmpty()) {
            return resultado
        } else {
            throw ListEntityNotFoundException(TarjetaGrafica::class.java)
        }
    }

    fun createGrafica(graficaNueva: TarjetaGrafica, token:String): TarjetaGrafica {
        var usuario = usuRepo.findById(jwt.getUserIdFromJWT(token.split(" ")
                .toTypedArray()[1])).orElseThrow {
            SingleEntityNotFoundException(jwt.getUserIdFromJWT(token.split(" ")
                    .toTypedArray()[1]).toString(), Usuario::class.java)
        }
        var grafica = graficaRepo.save(graficaNueva)
        graficaRepo.save(grafica)

        return grafica
    }
}