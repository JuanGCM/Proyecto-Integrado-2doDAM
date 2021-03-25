package com.salesianostriana.dam.virtualgaming.services

import com.salesianostriana.dam.virtualgaming.dtos.MisOrdenadoresDTO
import com.salesianostriana.dam.virtualgaming.dtos.toDto
import com.salesianostriana.dam.virtualgaming.errors.SingleEntityNotFoundException
import com.salesianostriana.dam.virtualgaming.models.Ordenador
import com.salesianostriana.dam.virtualgaming.models.Usuario
import com.salesianostriana.dam.virtualgaming.repositories.OrdenadorRepository
import com.salesianostriana.dam.virtualgaming.repositories.UsuarioRepository
import com.salesianostriana.dam.virtualgaming.repositories.VideojuegoRepository
import com.salesianostriana.dam.virtualgaming.security.JwtTokenProvider
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class OrdenadorServicio {

    @Autowired
    lateinit var ordenadorRepo:OrdenadorRepository

    @Autowired
    lateinit var usuRepo:UsuarioRepository

    @Autowired
    lateinit var juegoRepo:VideojuegoRepository

    @Autowired
    lateinit var jwt: JwtTokenProvider

    fun getMisOrdenadores(token:String): List<Ordenador>{
        var usuario = usuRepo.findById(jwt.getUserIdFromJWT(token.split(" ")
                .toTypedArray()[1])).orElseThrow {
            SingleEntityNotFoundException(jwt.getUserIdFromJWT(token.split(" ")
                    .toTypedArray()[1]).toString(), Usuario::class.java)
        }
        var ordens = ordenadorRepo.findByUsuario(usuario)
        usuario.ordenadores = ordens
        usuRepo.save(usuario)

        return usuario.ordenadores
    }

    fun createMiOrdenador(ordenadorNuevo:Ordenador, token:String): ResponseEntity<MisOrdenadoresDTO>{
        var usuario = usuRepo.findById(jwt.getUserIdFromJWT(token.split(" ")
                .toTypedArray()[1])).orElseThrow {
            SingleEntityNotFoundException(jwt.getUserIdFromJWT(token.split(" ")
                    .toTypedArray()[1]).toString(), Usuario::class.java)
        }

        var ordenador = ordenadorRepo.save(ordenadorNuevo)


        ordenadorRepo.save(ordenador)
        usuario.ordenadores.add(ordenador)
        usuRepo.save(usuario)
        return ResponseEntity.status(HttpStatus.CREATED).body(ordenador.toDto())
    }

    fun createRequisitoJuego(ordenadorNuevo: Ordenador):Ordenador{

        var ordenador = ordenadorRepo.save(ordenadorNuevo)
        ordenadorRepo.save(ordenador)

        return ordenador
    }

    fun modifyOrdenador(id: Long, ordenadorNuevo: Ordenador): ResponseEntity<MisOrdenadoresDTO> = ordenadorRepo.findById(id)
            .map { ordenadorAModificar ->
                ordenadorAModificar.titulo = ordenadorNuevo.titulo
                ordenadorAModificar.procesador = ordenadorNuevo.procesador
                ordenadorAModificar.ram = ordenadorNuevo.ram
                ordenadorAModificar.grafica = ordenadorNuevo.grafica
                ResponseEntity.status(HttpStatus.OK).body(ordenadorRepo.save(ordenadorAModificar).toDto())
            }.orElseThrow {
                SingleEntityNotFoundException(id.toString(),Ordenador::class.java)
   }

    fun deleteOrdenador(id:Long):ResponseEntity<Any>{
        if(ordenadorRepo.existsById(id)){

            ordenadorRepo.deleteById(id)

        }
        return ResponseEntity.noContent().build()
    }

}