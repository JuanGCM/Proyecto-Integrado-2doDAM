package com.salesianostriana.dam.virtualgaming.models

import javax.persistence.*
import javax.validation.constraints.NotBlank

@Entity
class MemoriaRAM(

        @NotBlank(message = "{memoriaram.version.blank}")
        var version:String,

        @OneToMany(mappedBy = "minMemoriaRAM")
        var videojuego:Videojuego,

        @OneToMany(mappedBy = "ram")
        var ordenador: Ordenador,

        @Id @GeneratedValue
        var id:Long
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