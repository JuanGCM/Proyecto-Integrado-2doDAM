package com.example.realestate.ui.registro

import java.util.*

data class RegisterRequest(
    val username: String,
    val password: String,
    var email:String,
    var nombreCompleto:String,
    var fechaNacimiento: String//Date
)