package com.salesianostriana.dam.virtualgaming.repositories

import com.salesianostriana.dam.virtualgaming.models.Ordenador
import com.salesianostriana.dam.virtualgaming.models.Usuario
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface OrdenadorRepository :JpaRepository<Ordenador,Long>{

    fun findByTitulo(titulo:String):Ordenador

    fun findByUsuario(usuario: Usuario):Ordenador
}