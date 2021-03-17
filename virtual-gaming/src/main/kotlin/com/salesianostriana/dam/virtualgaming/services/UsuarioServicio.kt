package com.salesianostriana.dam.virtualgaming.services

import com.salesianostriana.dam.virtualgaming.errors.SingleEntityNotFoundException
import com.salesianostriana.dam.virtualgaming.models.Usuario
import com.salesianostriana.dam.virtualgaming.repositories.UsuarioRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.util.*

@Service
class UsuarioServicio {

    @Autowired
    lateinit var usuRepo:UsuarioRepository

    @Autowired
    lateinit var encoder: PasswordEncoder

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
}