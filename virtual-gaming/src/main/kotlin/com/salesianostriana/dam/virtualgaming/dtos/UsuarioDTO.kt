package com.salesianostriana.dam.virtualgaming.dtos

import com.salesianostriana.dam.virtualgaming.models.Usuario
import io.swagger.annotations.ApiModelProperty
import java.time.LocalDate
import java.util.*
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Past

data class UsuarioDTO (

        @get:NotBlank(message="{usuario.nombrecompleto.blank}")
        var nombreCompleto:String,

        @get:NotBlank(message="{usuario.username.blank}")
        var username:String,

        @get:NotBlank(message="{usuario.email.blank}")
        @get:Email(message="{usuario.email.email}")
        var email:String,

        @get:Past(message="{usuario.fechanacimiento.date}")
        var fechaNacimiento: LocalDate,

        var id: UUID?
)

fun Usuario.toDto(): UsuarioDTO = UsuarioDTO(
        nombreCompleto,
        username,
        email,
        fechaNacimiento,
        id)

data class DtoUserEdit(

        @get:NotBlank(message="{usuario.nombrecompleto.blank}")
        var nombreCompleto : String,

        @get:Email(message="{usuario.email.email}")
        var email : String,

        @get:NotBlank(message="{usuario.fechaNacimiento.blank}")
        var fechaNacimiento : String
)