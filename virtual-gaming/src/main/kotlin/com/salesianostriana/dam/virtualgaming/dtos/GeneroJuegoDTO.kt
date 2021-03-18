package com.salesianostriana.dam.virtualgaming.dtos

import com.salesianostriana.dam.virtualgaming.models.GeneroJuego
import javax.validation.constraints.NotBlank

data class GeneroJuegoDTO(var id:Long,

                             @get:NotBlank(message="{generojuego.titulo.blank}")
                             var titulo: String
)

fun GeneroJuego.toDto() = GeneroJuegoDTO(
        id!!,
        titulo
)