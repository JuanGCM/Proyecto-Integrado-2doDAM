package com.salesianostriana.dam.virtualgaming.services

import com.salesianostriana.dam.virtualgaming.dtos.DtoUserEdit
import com.salesianostriana.dam.virtualgaming.dtos.UsuarioDTO
import com.salesianostriana.dam.virtualgaming.dtos.toDto
import com.salesianostriana.dam.virtualgaming.errors.SingleEntityNotFoundException
import com.salesianostriana.dam.virtualgaming.models.Usuario
import com.salesianostriana.dam.virtualgaming.repositories.UsuarioRepository
import com.salesianostriana.dam.virtualgaming.security.JwtTokenProvider
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

@Service
class UsuarioServicio {

    @Autowired
    lateinit var usuRepo:UsuarioRepository

    @Autowired
    lateinit var encoder: PasswordEncoder

    @Autowired
    lateinit var jwt: JwtTokenProvider

    fun findByUsername(username:String) =
            usuRepo.findByUsername(username)

    fun create(nuevo:Usuario):Optional<Usuario>{
        if(findByUsername(nuevo.username).isPresent)
            return Optional.empty()

        return Optional.of(
                with(nuevo){
                    usuRepo.save(Usuario(
                            nuevo.nombreCompleto,
                            nuevo.username,
                            encoder.encode(nuevo.password),
                            nuevo.email,
                            nuevo.fechaNacimiento,
                            "USER"
                    ))
                }
        )
    }

    fun findById(id: UUID)=
            usuRepo.findById(id).orElseThrow {
                SingleEntityNotFoundException(
                        id.toString(),
                        Usuario::class.java
                ) }

    fun findByUsuario(username:String) = usuRepo.findByUsername(username).orElseThrow {
            SingleEntityNotFoundException(username,Usuario::class.java)
    }

    fun modifyUsuario(token:String, usuNuevo: DtoUserEdit): ResponseEntity<UsuarioDTO> =
            usuRepo.findById(jwt.getUserIdFromJWT(token.split(" ").toTypedArray()[1]))
                    .map { usuEditado ->
                        usuEditado.nombreCompleto = usuNuevo.nombreCompleto
                        usuEditado.email = usuNuevo.email
                        usuEditado.fechaNacimiento = LocalDate.of((usuNuevo.fechaNacimiento.split("-")[0]).toInt(),(usuNuevo.fechaNacimiento.split("-")[1]).toInt(),(usuNuevo.fechaNacimiento.split("-")[2]).toInt())
                        ResponseEntity.status(HttpStatus.OK).body(usuRepo.save(usuEditado).toDto())
                    }.orElseThrow {
                        SingleEntityNotFoundException(token,Usuario::class.java)
                    }
}