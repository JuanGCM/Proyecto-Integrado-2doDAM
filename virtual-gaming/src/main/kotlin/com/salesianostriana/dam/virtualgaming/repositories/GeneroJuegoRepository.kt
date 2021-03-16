package com.salesianostriana.dam.virtualgaming.repositories

import com.salesianostriana.dam.virtualgaming.models.GeneroJuego
import org.springframework.data.jpa.repository.JpaRepository

interface GeneroJuegoRepository:JpaRepository<GeneroJuego,Long> {

    fun findByTitulo(titulo:String):List<GeneroJuego>
}