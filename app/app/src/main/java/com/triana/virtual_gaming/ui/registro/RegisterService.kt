package com.triana.virtual_gaming.ui.registro

import com.triana.virtual_gaming.ui.registro.RegisterRequest
import com.triana.virtual_gaming.ui.registro.RegisterResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface RegisterService {

    @POST("/auth/register")
    fun register(@Body requestRegistro: RegisterRequest): Call<RegisterResponse>
}