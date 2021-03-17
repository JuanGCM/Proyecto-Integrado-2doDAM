package com.salesianostriana.dam.virtualgaming.repositories

import com.salesianostriana.dam.virtualgaming.models.Ordenador
import org.springframework.data.jpa.repository.JpaRepository

interface OrdenadorRepository :JpaRepository<Ordenador,Long>{

    fun findByTitulo(titulo:String):List<Ordenador>
}