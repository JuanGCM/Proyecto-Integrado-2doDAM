package com.salesianostriana.dam.virtualgaming.dtos


import com.salesianostriana.dam.virtualgaming.models.MemoriaRAM
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class MemoriaRAMDTO(var id:Long,

                         @NotBlank(message = "{memoriaram.titulo.blank}")
                         var titulo:String,

                        @NotNull(message = "{memoriaram.code.null}")
                        var code:Int
)

fun MemoriaRAM.toDto() = MemoriaRAMDTO(
        id!!,
        titulo,
        code
)