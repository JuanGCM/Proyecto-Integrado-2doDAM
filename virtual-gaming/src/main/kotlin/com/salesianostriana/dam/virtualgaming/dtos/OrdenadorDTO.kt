package com.salesianostriana.dam.virtualgaming.dtos

import com.salesianostriana.dam.virtualgaming.models.Ordenador
import javax.validation.constraints.NotBlank


data class MisOrdenadoresDTO(var id:Long,

                          @get:NotBlank(message="{ordenador.titulo.blank}")
                          var titulo: String,

                          var procesador: ProcesadorDTO,

                          var ram: MemoriaRAMDTO,

                          var grafica: TarjetaGraficaDTO
                      )

fun Ordenador.toDto() = MisOrdenadoresDTO(
        id!!,
        titulo,
        procesador.toDto(),
        ram.toDto(),
        grafica.toDto()
)