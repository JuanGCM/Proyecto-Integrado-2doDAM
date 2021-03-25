package com.triana.virtual_gaming.ui.videojuegos

import com.triana.virtual_gaming.ui.login.User
import com.triana.virtual_gaming.ui.models.JuegoDetalle
import com.triana.virtual_gaming.ui.models.UnVideojuego
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface VideojuegoServicio{

    @GET("/videojuegos")
    fun getvideojuegos(): Call<List<JuegoDetalle>>

    @GET("/user/{username}")
    fun getUsuario(@Path("username") username:String):Call<User>

    /*
    @GET("/viviendas/mine")
    fun getMyViviendas(@Header("Authorization") auth:String): Call<List<MyViviendasItem>>

     */
}