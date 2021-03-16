package com.salesianostriana.dam.virtualgaming.models

import javax.persistence.*
import javax.validation.constraints.NotBlank

@Entity
class Ordenador(

        @get:NotBlank(message="{usuario.password.blank}")
        private var titulo:String,

        @OneToOne(cascade = arrayOf(CascadeType.ALL))
        @JoinColumn(name = "procesador_id")
        private var procesador:Procesador,

        @get:NotBlank(message="{usuario.password.blank}")
        private var ram:Int,

        @OneToOne(cascade = arrayOf(CascadeType.ALL))
        @JoinColumn(name = "tarjetagrafica_id")
        private var grafica:TarjetaGrafica,

        @OneToOne(mappedBy = "requisitos")
        private var videojuego: Videojuego,

        @ManyToOne
        private var usuario:Usuario,

        @Id @GeneratedValue
        private var id:Long?

){
        override fun equals(other: Any?): Boolean {
                if (this === other) return true
                if (other == null || javaClass != other.javaClass) return false
                val that = other as Ordenador
                if (id != that.id) return false
                return true
        }

        override fun hashCode(): Int {
                return if (id != null)
                        id.hashCode()
                else 0
        }
}