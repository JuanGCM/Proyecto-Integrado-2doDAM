package com.salesianostriana.dam.virtualgaming.repositories

import com.salesianostriana.dam.virtualgaming.models.MemoriaRAM
import com.salesianostriana.dam.virtualgaming.models.Videojuego
import org.springframework.data.jpa.repository.JpaRepository

interface MemoriaRAMRepository: JpaRepository<MemoriaRAM, Long> {

    fun findMemoriaByVideojuego(videojuego: Videojuego): MemoriaRAM

    fun findByCode(codigo:Int): MemoriaRAM
}