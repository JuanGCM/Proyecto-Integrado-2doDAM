package com.salesianostriana.dam.virtualgaming.services

import com.salesianostriana.dam.virtualgaming.dtos.ProcesadorDTO
import com.salesianostriana.dam.virtualgaming.dtos.TarjetaGraficaDTO
import com.salesianostriana.dam.virtualgaming.dtos.toDto
import com.salesianostriana.dam.virtualgaming.errors.ListEntityNotFoundException
import com.salesianostriana.dam.virtualgaming.errors.SingleEntityNotFoundException
import com.salesianostriana.dam.virtualgaming.models.Procesador
import com.salesianostriana.dam.virtualgaming.models.TarjetaGrafica
import com.salesianostriana.dam.virtualgaming.models.Usuario
import com.salesianostriana.dam.virtualgaming.repositories.ProcesadorRepository
import com.salesianostriana.dam.virtualgaming.repositories.UsuarioRepository
import com.salesianostriana.dam.virtualgaming.security.JwtTokenProvider
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class ProcesadorServicio {

    @Autowired
    lateinit var procesadorRepo:ProcesadorRepository

    @Autowired
    lateinit var usuRepo: UsuarioRepository

    @Autowired
    lateinit var jwt: JwtTokenProvider

    fun findAll():List<Procesador> {
        var resultado = procesadorRepo.findAll()
        if (!resultado.isEmpty()) {
            return resultado
        } else {
            throw ListEntityNotFoundException(Procesador::class.java)
        }
    }

    fun createProcesador(procesadorNuevo: Procesador, token:String): Procesador {
        var usuario = usuRepo.findById(jwt.getUserIdFromJWT(token.split(" ")
                .toTypedArray()[1])).orElseThrow {
            SingleEntityNotFoundException(jwt.getUserIdFromJWT(token.split(" ")
                    .toTypedArray()[1]).toString(), Usuario::class.java)
        }
        var procesador = procesadorRepo.save(procesadorNuevo)

        return procesador
    }

    fun deleteProcesador(id: Long): ResponseEntity<Any>{
        if(procesadorRepo.existsById(id)){
            procesadorRepo.deleteById(id)
        }
        return ResponseEntity.noContent().build()
    }
}