package com.example.realestate.ui

data class UnaVivienda(
        val id: Int,
        val titulo: String,
        val precio: Double,
        val metrosCuadrados: Int,
        val numeroHabitaciones: Int,
        val localidad: String,
        val provincia: String,
        val categoria: String
)