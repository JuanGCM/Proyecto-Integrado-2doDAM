package com.salesianostriana.dam.virtualgaming.models

import javax.persistence.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

@Entity
class TarjetaGrafica(

        @get:NotBlank(message = "{tarjetagrafica.titulo.blank}")
        var titulo:String,

        @get:NotNull(message = "{tarjetagrafica.code.null}")
        var code:Int,

        @OneToMany(mappedBy = "grafica")
        var ordenadores:MutableList<Ordenador>? = mutableListOf(),

        @OneToMany(mappedBy = "minTarjetaGrafica")
        var videojuego:MutableList<Videojuego>? = mutableListOf(),

        @Id @GeneratedValue
        var id:Long?

) {
        override fun equals(other: Any?): Boolean {
                if (this === other) return true
                if (other == null || javaClass != other.javaClass) return false
                val that = other as TarjetaGrafica
                if (id != that.id) return false
                return true
        }

        override fun hashCode(): Int {
                return if (id != null)
                        id.hashCode()
                else 0
        }
}