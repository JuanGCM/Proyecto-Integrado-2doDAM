package com.salesianostriana.dam.virtualgaming.repositories

import com.salesianostriana.dam.virtualgaming.models.Procesador
import com.salesianostriana.dam.virtualgaming.models.Videojuego
import org.springframework.data.jpa.repository.JpaRepository

interface ProcesadorRepository: JpaRepository<Procesador, Long> {

    fun findProcesadorByVideojuego(videojuego: Videojuego): Procesador
}