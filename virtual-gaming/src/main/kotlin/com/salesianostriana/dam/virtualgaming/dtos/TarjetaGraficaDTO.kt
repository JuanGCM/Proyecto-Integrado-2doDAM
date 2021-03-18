package com.salesianostriana.dam.virtualgaming.dtos

import com.salesianostriana.dam.virtualgaming.models.TarjetaGrafica
import javax.validation.constraints.NotBlank

data class TarjetaGraficaDTO(var id:Long,

                             @get:NotBlank(message="{tarjetagrafica.fabricante.blank}")
                             var fabricante: String
)

fun TarjetaGrafica.toDto() = TarjetaGraficaDTO(
        id!!,
        fabricante
)