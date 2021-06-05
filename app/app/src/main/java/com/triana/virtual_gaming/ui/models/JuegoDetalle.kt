package com.triana.virtual_gaming.ui.models

data class JuegoDetalle (
        val id:Int,
        val nombre:String,
        val descripcion:String,
        val precio:Double,
        val plataforma:String,
        val img:String,
        val minProcesador:Procesador,
        val minMemoriaRAM:MemoriaRAM,
        val minTarjetaGrafica:TarjetaGrafica
)