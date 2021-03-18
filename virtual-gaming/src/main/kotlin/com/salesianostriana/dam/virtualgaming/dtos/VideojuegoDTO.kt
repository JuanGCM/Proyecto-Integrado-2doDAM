package com.salesianostriana.dam.virtualgaming.dtos

import com.salesianostriana.dam.virtualgaming.models.Videojuego
import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank


data class ListadoVideojuegoDTO(var id:Long,

                              @get:NotBlank(message="{videojuego.nombre.blank}")
                              var nombre: String,

                              @get:Min(0, message = "{videojuego.precio.min}")
                              var precio: Double,

                              var imagen: ImagenDTO)

data class UnVideojuegoDTO(var id:Long,

                          @get:NotBlank(message="{videojuego.nombre.blank}")
                          var nombre: String,

                          @get:NotBlank(message="{videojuego.descripcion.blank}")
                          var descripcion: String,

                          @get:Min(0, message = "{videojuego.precio.min}")
                          var precio: Double,

                          @get:NotBlank(message="{videojuego.plataforma.blank}")
                          var plataforma: String,

                          //var generoJuegos:GeneroJuegoDTO,

                          var minProcesador:ProcesadorDTO,

                          var minTarjetaGrafica:TarjetaGraficaDTO,

                          var minMemoriaRAM:MemoriaRAMDTO,

                          var imagen: ImagenDTO)


fun Videojuego.toDto() = ListadoVideojuegoDTO(
        id!!,
        nombre,
        precio,
        imagen.toDto()
)

fun Videojuego.toSpecificDto() = UnVideojuegoDTO(
        id!!,
        nombre,
        descripcion,
        precio,
        plataforma,
        //generoJuegos.toDto(),
        minProcesador.toDto(),
        minTarjetaGrafica.toDto(),
        minMemoriaRAM.toDto(),
        imagen.toDto()
)