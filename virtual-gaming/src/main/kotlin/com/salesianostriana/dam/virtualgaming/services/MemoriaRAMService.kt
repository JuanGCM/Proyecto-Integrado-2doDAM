package com.salesianostriana.dam.virtualgaming.services

import com.salesianostriana.dam.virtualgaming.dtos.MemoriaRAMDTO
import com.salesianostriana.dam.virtualgaming.dtos.ProcesadorDTO
import com.salesianostriana.dam.virtualgaming.dtos.toDto
import com.salesianostriana.dam.virtualgaming.errors.ListEntityNotFoundException
import com.salesianostriana.dam.virtualgaming.errors.SingleEntityNotFoundException
import com.salesianostriana.dam.virtualgaming.models.MemoriaRAM
import com.salesianostriana.dam.virtualgaming.models.Procesador
import com.salesianostriana.dam.virtualgaming.models.Usuario
import com.salesianostriana.dam.virtualgaming.repositories.MemoriaRAMRepository
import com.salesianostriana.dam.virtualgaming.repositories.UsuarioRepository
import com.salesianostriana.dam.virtualgaming.security.JwtTokenProvider
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class MemoriaRAMService {

    @Autowired
    lateinit var ramRepo:MemoriaRAMRepository

    @Autowired
    lateinit var usuRepo: UsuarioRepository

    @Autowired
    lateinit var jwt: JwtTokenProvider

    fun findAll():List<MemoriaRAM> {
        var resultado = ramRepo.findAll()
        if (!resultado.isEmpty()) {
            return resultado
        } else {
            throw ListEntityNotFoundException(MemoriaRAM::class.java)
        }
    }

    fun createMemoriaRAM(memoriaNueva: MemoriaRAM, token:String): MemoriaRAM {
        var usuario = usuRepo.findById(jwt.getUserIdFromJWT(token.split(" ")
                .toTypedArray()[1])).orElseThrow {
            SingleEntityNotFoundException(jwt.getUserIdFromJWT(token.split(" ")
                    .toTypedArray()[1]).toString(), Usuario::class.java)
        }
        var memoria = ramRepo.save(memoriaNueva)
        ramRepo.save(memoria)

        return memoria
    }
}