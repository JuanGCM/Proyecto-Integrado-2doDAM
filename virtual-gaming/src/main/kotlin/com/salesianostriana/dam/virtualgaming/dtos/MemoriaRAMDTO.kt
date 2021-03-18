package com.salesianostriana.dam.virtualgaming.dtos


import com.salesianostriana.dam.virtualgaming.models.MemoriaRAM
import javax.validation.constraints.NotBlank

data class MemoriaRAMDTO(var id:Long,

                         @NotBlank(message = "{memoriaram.version.blank}")
                         var version:String
)

fun MemoriaRAM.toDto() = MemoriaRAMDTO(
        id!!,
        version
)