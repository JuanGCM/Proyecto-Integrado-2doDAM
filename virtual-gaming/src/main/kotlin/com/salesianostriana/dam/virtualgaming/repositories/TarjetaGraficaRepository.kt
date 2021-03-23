package com.salesianostriana.dam.virtualgaming.repositories

import com.salesianostriana.dam.virtualgaming.models.TarjetaGrafica
import com.salesianostriana.dam.virtualgaming.models.Videojuego
import org.springframework.data.jpa.repository.JpaRepository

interface TarjetaGraficaRepository: JpaRepository<TarjetaGrafica, Long> {

    fun findGraficaByVideojuego(videojuego: Videojuego): TarjetaGrafica

    fun findByCode(codigo:Int):TarjetaGrafica
}