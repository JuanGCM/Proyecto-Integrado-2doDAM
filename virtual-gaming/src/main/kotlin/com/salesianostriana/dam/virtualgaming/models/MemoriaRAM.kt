package com.salesianostriana.dam.virtualgaming.models

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.validation.constraints.NotBlank

@Entity
class MemoriaRAM(

        @NotBlank(message = "{memoriaram.version.blank}")
        var version:String,

        @Id @GeneratedValue
        var id:Long
) {
}