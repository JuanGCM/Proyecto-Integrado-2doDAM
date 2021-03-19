package com.salesianostriana.dam.virtualgaming.services

import com.salesianostriana.dam.virtualgaming.dtos.GeneroJuegoDTO
import com.salesianostriana.dam.virtualgaming.dtos.MemoriaRAMDTO
import com.salesianostriana.dam.virtualgaming.dtos.toDto
import com.salesianostriana.dam.virtualgaming.errors.ListEntityNotFoundException
import com.salesianostriana.dam.virtualgaming.errors.SingleEntityNotFoundException
import com.salesianostriana.dam.virtualgaming.models.GeneroJuego
import com.salesianostriana.dam.virtualgaming.models.MemoriaRAM
import com.salesianostriana.dam.virtualgaming.models.Usuario
import com.salesianostriana.dam.virtualgaming.repositories.GeneroJuegoRepository
import com.salesianostriana.dam.virtualgaming.repositories.UsuarioRepository
import com.salesianostriana.dam.virtualgaming.security.JwtTokenProvider
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class GeneroJuegoServicio {

    @Autowired
    lateinit var generoRepo:GeneroJuegoRepository

    @Autowired
    lateinit var usuRepo: UsuarioRepository

    @Autowired
    lateinit var jwt: JwtTokenProvider

    fun findAll():List<GeneroJuego> {
        var resultado = generoRepo.findAll()
        if (!resultado.isEmpty()) {
            return resultado
        } else {
            throw ListEntityNotFoundException(GeneroJuego::class.java)
        }
    }

    fun createGeneroJuego(generoNuevo: GeneroJuego, token:String): GeneroJuego {
        var usuario = usuRepo.findById(jwt.getUserIdFromJWT(token.split(" ")
                .toTypedArray()[1])).orElseThrow {
            SingleEntityNotFoundException(jwt.getUserIdFromJWT(token.split(" ")
                    .toTypedArray()[1]).toString(), Usuario::class.java)
        }
        var genero = generoRepo.save(generoNuevo)
        generoRepo.save(genero)

        return genero
    }
}