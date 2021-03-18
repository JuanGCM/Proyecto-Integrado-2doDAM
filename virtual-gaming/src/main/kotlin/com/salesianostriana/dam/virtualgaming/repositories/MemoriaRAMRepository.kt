package com.salesianostriana.dam.virtualgaming.repositories

import com.salesianostriana.dam.virtualgaming.models.MemoriaRAM
import org.springframework.data.jpa.repository.JpaRepository

interface MemoriaRAMRepository: JpaRepository<MemoriaRAM, Long> {
}