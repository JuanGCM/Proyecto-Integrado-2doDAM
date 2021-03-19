package com.example.realestate.ui.login

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    val refreshToken: String,
    val token: String,
    val user: User
)