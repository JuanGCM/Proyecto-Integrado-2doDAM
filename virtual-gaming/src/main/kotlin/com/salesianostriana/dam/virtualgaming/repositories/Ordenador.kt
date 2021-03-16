package com.salesianostriana.dam.virtualgaming.repositories

import com.salesianostriana.dam.virtualgaming.models.Videojuego
import org.springframework.data.jpa.repository.JpaRepository

interface Ordenador :JpaRepository<Ordenador,Long>{

    fun findByTitulo(titulo:String):List<Ordenador>
}