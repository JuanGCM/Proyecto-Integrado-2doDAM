package com.triana.virtual_gaming.ui.login

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginService {

    @POST("/auth/login")
    fun login(@Body requestLogin: LoginRequest): Call<LoginResponse>
}