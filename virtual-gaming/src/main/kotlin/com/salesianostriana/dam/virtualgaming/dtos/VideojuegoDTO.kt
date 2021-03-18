package com.salesianostriana.dam.virtualgaming.dtos

import com.salesianostriana.dam.virtualgaming.models.MemoriaRAM
import com.salesianostriana.dam.virtualgaming.models.Procesador
import com.salesianostriana.dam.virtualgaming.models.TarjetaGrafica
import com.salesianostriana.dam.virtualgaming.models.Videojuego
import org.hibernate.validator.constraints.Range
import javax.persistence.Lob
import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

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

                          var generoJuego:GeneroJuegoDTO,

                          var minProcesador:ProcesadorDTO,

                          var minTarjetaGrafica:TarjetaGraficaDTO,

                          var minMemoriaRAM:MemoriaRAMDTO,

                          var imagen: ImagenDTO)


fun Videojuego.toDto() = ListadoVideojuegoDTO(
        id!!,
        nombre,
        precio,
        imagen
)

fun Videojuego.toSpecificDto() = UnVideojuegoDTO(
        id!!,
        nombre,
        descripcion,
        precio,
        plataforma,
        generoJuego,
        minProcesador,
        minTarjetaGrafica,
        minMemoriaRAM,
        imagen
)