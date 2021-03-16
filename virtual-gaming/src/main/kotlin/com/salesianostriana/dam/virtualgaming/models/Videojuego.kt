package com.salesianostriana.dam.virtualgaming.models

import javax.persistence.*
import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

@Entity
class Videojuego(

        @get:NotBlank(message="{videojuego.nombre.blank}")
        private var nombre:String,

        @get:NotBlank(message="{videojuego.descripcion.blank}")
        private var descripcion:String,

        @get:Min(0, message = "{videojuego.precio.min}")
        @get:NotNull(message = "{videojuego.precio.null}")
        private var precio:Double,

        @get:NotBlank(message="{videojuego.plataforma.blank}")
        private var plataforma:String,

        @OneToMany(mappedBy = "videojuego")
        private var generoJuego:MutableList<GeneroJuego> = mutableListOf(),

        @OneToOne(cascade = arrayOf(CascadeType.ALL))
        @JoinColumn(name = "ordenador_id")
        private var requisitos:Ordenador,

        @OneToOne(cascade = arrayOf(CascadeType.ALL))
        @JoinColumn(name = "imagen_id")
        private var imagen:ImagenVideojuego,

        @ManyToMany
        var likes: MutableList<Usuario>?= mutableListOf(),

        @Id @GeneratedValue
        private var id:Long?
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || javaClass != other.javaClass) return false
        val that = other as Videojuego
        if (id != that.id) return false
        return true
    }

    override fun hashCode(): Int {
        return if (id != null)
            id.hashCode()
        else 0
    }
}