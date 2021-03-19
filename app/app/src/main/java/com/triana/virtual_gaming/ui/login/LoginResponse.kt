package com.triana.virtual_gaming.ui.login

import com.triana.virtual_gaming.ui.login.User
import com.google.gson.annotations.SerializedName

data class LoginResponse(
    val refreshToken: String,
    val token: String,
    val user: User
)