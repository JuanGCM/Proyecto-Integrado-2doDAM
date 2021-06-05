package com.salesianostriana.dam.virtualgaming.dtos

import com.salesianostriana.dam.virtualgaming.models.Videojuego
import javax.persistence.Lob
import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull


data class ListadoVideojuegoDTO(var id:Long,
                                @get:NotBlank(message="{videojuego.nombre.blank}")
                                var nombre: String,
                                @get:NotBlank(message="{videojuego.descripcion.blank}")
                                var descripcion: String,
                                @get:Min(0, message = "{videojuego.precio.min}")
                                var precio: Double,
                                @get:NotBlank(message="{videojuego.plataforma.blank}")
                                var plataforma: String,
                                var img:String
)

data class UnVideojuegoDTO(var id:Long,

                          @get:NotBlank(message="{videojuego.nombre.blank}")
                          var nombre: String,

                          @Lob
                          @get:NotNull
                          var descripcion: String,

                          @get:Min(0, message = "{videojuego.precio.min}")
                          var precio: Double,

                          @get:NotBlank(message="{videojuego.plataforma.blank}")
                          var plataforma: String,

                          var img:String,

                          var minProcesador:ProcesadorDTO?,

                          var minTarjetaGrafica:TarjetaGraficaDTO?,

                          var minMemoriaRAM:MemoriaRAMDTO?)


fun Videojuego.toDto() = ListadoVideojuegoDTO(
        id!!,
        nombre,
        descripcion,
        precio,
        plataforma,
        img
)

fun Videojuego.toSpecificDto() = UnVideojuegoDTO(
        id!!,
        nombre,
        descripcion,
        precio,
        plataforma,
        img,
        minProcesador!!.toDto(),
        minTarjetaGrafica!!.toDto(),
        minMemoriaRAM!!.toDto())