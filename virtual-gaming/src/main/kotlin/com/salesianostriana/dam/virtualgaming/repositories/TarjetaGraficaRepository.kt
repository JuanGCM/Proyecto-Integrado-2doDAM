package com.salesianostriana.dam.virtualgaming.repositories

import com.salesianostriana.dam.virtualgaming.models.TarjetaGrafica
import org.springframework.data.jpa.repository.JpaRepository

interface TarjetaGraficaRepository: JpaRepository<TarjetaGrafica, Long> {
}