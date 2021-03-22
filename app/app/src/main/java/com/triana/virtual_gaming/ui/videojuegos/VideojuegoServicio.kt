package com.triana.virtual_gaming.ui.videojuegos

import com.triana.virtual_gaming.ui.login.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface VideojuegoServicio{

    @GET("/videojuegos")
    fun getvideojuegos(): Call<List<UnVideojuego>>

    @GET("/user/{username}")
    fun getUsuario(@Path("username") username:String):Call<User>

    /*
    @GET("/viviendas/mine")
    fun getMyViviendas(@Header("Authorization") auth:String): Call<List<MyViviendasItem>>

     */
}