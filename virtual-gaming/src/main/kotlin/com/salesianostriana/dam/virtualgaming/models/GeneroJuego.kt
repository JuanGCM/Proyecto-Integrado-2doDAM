package com.salesianostriana.dam.virtualgaming.models

import javax.persistence.*
import javax.validation.constraints.NotBlank

@Entity
class GeneroJuego (

        @get:NotBlank(message = "{generojuego.titulo.blank}")
        var titulo:String,

        @ManyToMany
        var videojuegos:MutableList<Videojuego>? = mutableListOf(),

        @Id @GeneratedValue
        var id:Long?
) {
        override fun equals(other: Any?): Boolean {
                if (this === other) return true
                if (other == null || javaClass != other.javaClass) return false
                val that = other as GeneroJuego
                if (id != that.id) return false
                return true
        }

        override fun hashCode(): Int {
                return if (id != null)
                        id.hashCode()
                else 0
        }

}
