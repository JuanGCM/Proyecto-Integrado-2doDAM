package com.salesianostriana.dam.virtualgaming.models

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.OneToOne
import javax.validation.constraints.NotBlank

@Entity
class TarjetaGrafica(

        @get:NotBlank(message = "{tarjetagrafica.fabricante.blank}")
        private var fabricante:String,

        @OneToOne(mappedBy = "grafica")
        private var ordenador: Ordenador,

        @Id @GeneratedValue
        private var id:Long?

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