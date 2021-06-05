package com.salesianostriana.dam.virtualgaming.repositories

import com.salesianostriana.dam.virtualgaming.models.Usuario
import com.salesianostriana.dam.virtualgaming.models.Videojuego
import org.springframework.data.jpa.repository.JpaRepository

interface VideojuegoRepository:JpaRepository<Videojuego,Long> {

    fun findByPrecio(precio:Double):List<Videojuego>
    fun findByLikesContains(usuario:Usuario): MutableList<Videojuego>
    fun findByPlataforma(plataforma:String):List<Videojuego>
    fun findByNombre(nombre:String):Videojuego
}