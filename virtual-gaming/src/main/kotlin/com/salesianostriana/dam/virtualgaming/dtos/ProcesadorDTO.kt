package com.salesianostriana.dam.virtualgaming.dtos


import com.salesianostriana.dam.virtualgaming.models.Procesador
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class ProcesadorDTO(var id:Long,

                         @NotBlank(message = "{procesador.titulo.blank}")
                         var titulo:String,

                         @NotNull(message = "{procesador.code.null}")
                         var code:Int,
)

fun Procesador.toDto() = ProcesadorDTO(
        id!!,
        titulo,
        code
)