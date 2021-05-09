package com.salesianostriana.dam.virtualgaming.controllers

import com.salesianostriana.dam.virtualgaming.dtos.DtoUserEdit
import com.salesianostriana.dam.virtualgaming.dtos.UsuarioDTO
import com.salesianostriana.dam.virtualgaming.dtos.toDto
import com.salesianostriana.dam.virtualgaming.models.Usuario
import com.salesianostriana.dam.virtualgaming.services.UsuarioServicio
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import java.util.*
import javax.validation.Valid

@RestController
@RequestMapping("/user")
class UsuarioController(
        val usuarioServicio: UsuarioServicio
){

    @PostMapping("/")
    fun nuevoUsuario(@Valid @RequestBody usuarioNuevo:Usuario):ResponseEntity<UsuarioDTO> =
            usuarioServicio.create(usuarioNuevo).map { ResponseEntity.status(
                    HttpStatus.CREATED
            ).body(it.toDto()) }.orElseThrow {
                ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "El nombre de usuario ${usuarioNuevo.username} ya existe"
                ) }

    @GetMapping("/{username}")
    fun getUsuarioPorUsername(@PathVariable username: String):ResponseEntity<UsuarioDTO> {
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioServicio.findByUsuario(username).toDto())
    }

    @PutMapping()
    fun modifyUsuario(@RequestHeader("Authorization") token:String,@Valid @RequestBody usuNuevo: DtoUserEdit)=
            usuarioServicio.modifyUsuario(token, usuNuevo)

}