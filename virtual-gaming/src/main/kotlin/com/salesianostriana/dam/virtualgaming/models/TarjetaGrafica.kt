package com.salesianostriana.dam.virtualgaming.models

import javax.persistence.*
import javax.validation.constraints.NotBlank

@Entity
class TarjetaGrafica(

        @get:NotBlank(message = "{tarjetagrafica.fabricante.blank}")
        var fabricante:String,

        @OneToOne(mappedBy = "grafica")
        var ordenador: Ordenador,

        @OneToMany(mappedBy = "minTarjetaGrafica")
        var videojuego:Videojuego,

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