package com.salesianostriana.dam.virtualgaming.dtos

import com.salesianostriana.dam.virtualgaming.models.TarjetaGrafica
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class TarjetaGraficaDTO(var id:Long,

                             @NotBlank(message = "{tarjetagrafica.titulo.blank}")
                             var titulo:String,

                             @NotNull(message = "{tarjetagrafica.code.null}")
                             var code:Int,
)

fun TarjetaGrafica.toDto() = TarjetaGraficaDTO(
        id!!,
        titulo,
        code
)