package com.salesianostriana.dam.virtualgaming.dtos

import com.salesianostriana.dam.virtualgaming.models.MemoriaRAM
import com.salesianostriana.dam.virtualgaming.models.Ordenador
import com.salesianostriana.dam.virtualgaming.models.Procesador
import com.salesianostriana.dam.virtualgaming.models.TarjetaGrafica
import java.rmi.server.UID
import java.util.*
import javax.validation.constraints.NotBlank


data class MisOrdenadoresDTO(var id: UUID,

                             @get:NotBlank(message="{ordenador.titulo.blank}")
                          var titulo: String,

                             var procesador: ProcesadorDTO?,

                             var ram: MemoriaRAMDTO?,

                             var grafica: TarjetaGraficaDTO?
                      )


fun Ordenador.toDto() = MisOrdenadoresDTO(
        id!!,
        titulo,
        procesador!!.toDto(),
        ram!!.toDto(),
        grafica!!.toDto()
)

data class MiOrdenador(
        @get:NotBlank(message="{ordenador.titulo.blank}")
        var titulo: String,

        var procesador: Procesador,

        var ram: MemoriaRAM,

        var grafica: TarjetaGrafica
)

