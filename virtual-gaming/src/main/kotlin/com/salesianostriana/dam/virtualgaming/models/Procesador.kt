package com.salesianostriana.dam.virtualgaming.models

import javax.persistence.*
import javax.validation.constraints.NotBlank

@Entity
class Procesador(

        @get:NotBlank(message = "{procesador.marca.blank}")
        var marca:String,

        @get:NotBlank(message = "{procesador.version.blank}")
        var version:String,

        @OneToMany(mappedBy = "procesador")
        var ordenadores:MutableList<Ordenador> = mutableListOf(),

        @OneToMany(mappedBy = "minProcesador")
        var videojuego:MutableList<Videojuego> = mutableListOf(),

        @Id @GeneratedValue
        var id:Long?
) {
        override fun equals(other: Any?): Boolean {
                if (this === other) return true
                if (other == null || javaClass != other.javaClass) return false
                val that = other as Procesador
                if (id != that.id) return false
                return true
        }

        override fun hashCode(): Int {
                return if (id != null)
                        id.hashCode()
                else 0
        }
}