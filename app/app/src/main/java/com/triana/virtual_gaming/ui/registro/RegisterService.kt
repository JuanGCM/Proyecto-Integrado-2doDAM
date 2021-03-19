package com.example.realestate.ui.registro

import com.example.realestate.ui.registro.RegisterRequest
import com.example.realestate.ui.registro.RegisterResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface RegisterService {

    @POST("/auth/register")
    fun register(@Body requestRegistro: RegisterRequest): Call<RegisterResponse>
}