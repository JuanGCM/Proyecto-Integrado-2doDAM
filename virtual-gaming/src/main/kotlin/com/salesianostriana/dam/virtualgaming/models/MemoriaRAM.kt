package com.salesianostriana.dam.virtualgaming.models

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.OneToMany
import javax.validation.constraints.NotBlank

@Entity
class MemoriaRAM(

        @NotBlank(message = "{memoriaram.version.blank}")
        var version:String,

        @OneToMany(mappedBy = "minMemoriaRAM")
        var videojuego:Videojuego,

        @Id @GeneratedValue
        var id:Long
) {
}