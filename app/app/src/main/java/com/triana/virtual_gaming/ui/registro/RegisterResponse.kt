package com.example.realestate.ui.registro

import java.util.*

data class RegisterResponse(
    val activo: Boolean,
    val email: String,
    val fechaNacimiento: Date,
    val id: String,
    val nombreCompleto: String,
    val username: String
)