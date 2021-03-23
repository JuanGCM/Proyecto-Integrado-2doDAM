package com.salesianostriana.dam.virtualgaming.models

import javax.persistence.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

@Entity
class MemoriaRAM(

        @get:NotBlank(message = "{memoriaram.titulo.blank}")
        var titulo:String,

        @get:NotNull(message = "{memoriaram.code.null}")
        var code:Int,

        @OneToMany(mappedBy = "minMemoriaRAM")
        var videojuego:MutableList<Videojuego>? = mutableListOf(),

        @OneToMany(mappedBy = "ram")
        var ordenadores:MutableList<Ordenador>? = mutableListOf(),

        @Id @GeneratedValue
        var id:Long?
) {

        override fun equals(other: Any?): Boolean {
                if (this === other) return true
                if (other == null || javaClass != other.javaClass) return false
                val that = other as MemoriaRAM
                if (id != that.id) return false
                return true
        }

        override fun hashCode(): Int {
                return if (id != null)
                        id.hashCode()
                else 0
        }
}