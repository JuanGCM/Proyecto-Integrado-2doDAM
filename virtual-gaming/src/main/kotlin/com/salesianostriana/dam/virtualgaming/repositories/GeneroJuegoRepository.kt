package com.salesianostriana.dam.virtualgaming.repositories

import com.salesianostriana.dam.virtualgaming.models.GeneroJuego
import com.salesianostriana.dam.virtualgaming.models.ImagenVideojuego
import com.salesianostriana.dam.virtualgaming.models.Videojuego
import org.springframework.data.jpa.repository.JpaRepository

interface GeneroJuegoRepository:JpaRepository<GeneroJuego,Long> {

    fun findByTitulo(titulo:String):GeneroJuego
}