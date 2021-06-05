package com.salesianostriana.dam.virtualgaming.repositories

import com.salesianostriana.dam.virtualgaming.models.Usuario
import com.salesianostriana.dam.virtualgaming.models.Videojuego
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface UsuarioRepository:JpaRepository<Usuario,UUID> {

    fun findByUsername(username : String) : Optional<Usuario>
    fun findByDeseadosContains(videojuego: Videojuego): MutableList<Usuario>
}