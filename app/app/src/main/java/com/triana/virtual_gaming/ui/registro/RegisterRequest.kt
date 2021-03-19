package com.triana.virtual_gaming.ui.registro

import java.util.*

data class RegisterRequest(
    var nombreCompleto:String,
    val username: String,
    val password: String,
    var email:String,
    var fechaNacimiento: String//Date
)
