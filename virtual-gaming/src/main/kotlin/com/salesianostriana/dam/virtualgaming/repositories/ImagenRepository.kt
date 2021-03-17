package com.salesianostriana.dam.virtualgaming.repositories

import com.salesianostriana.dam.virtualgaming.models.ImagenVideojuego
import com.salesianostriana.dam.virtualgaming.models.Videojuego
import org.springframework.data.jpa.repository.JpaRepository

interface ImagenRepository : JpaRepository<ImagenVideojuego, Long> {


    fun findByVideojuego(videojuego: Videojuego): ImagenVideojuego


}