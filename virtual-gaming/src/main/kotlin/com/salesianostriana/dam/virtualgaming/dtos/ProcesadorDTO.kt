package com.salesianostriana.dam.virtualgaming.dtos


import com.salesianostriana.dam.virtualgaming.models.Procesador
import javax.validation.constraints.NotBlank

data class ProcesadorDTO(var id:Long,

                         @NotBlank(message = "{procesador.marca.blank}")
                         var marca:String,

                         @NotBlank(message = "{procesador.version.blank}")
                         var version:String,
)

fun Procesador.toDto() = ProcesadorDTO(
        id!!,
        marca,
        version
)