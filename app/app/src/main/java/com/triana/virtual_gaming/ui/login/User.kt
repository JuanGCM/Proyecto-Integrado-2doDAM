package com.example.realestate.ui.login

data class User(
    val activo: Boolean,
    val email: String,
    val fechaNacimiento: String,
    val id: String,
    val nombreCompleto: String,
    val username: String
)