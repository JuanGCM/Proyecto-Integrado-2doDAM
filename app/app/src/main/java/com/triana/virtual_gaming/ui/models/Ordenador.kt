package com.triana.virtual_gaming.ui.models

import java.util.*

data class Ordenador(
        val id:Long,
        val titulo:String,
        val procesador: Procesador,
        val ram: MemoriaRAM,
        val grafica: TarjetaGrafica
)
