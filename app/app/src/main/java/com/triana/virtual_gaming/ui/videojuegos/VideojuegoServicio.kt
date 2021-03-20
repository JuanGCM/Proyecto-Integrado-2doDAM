package com.triana.virtual_gaming.ui.videojuegos

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface VideojuegoServicio{

    @GET("/videojuegos")
    fun getvideojuegos(): Call<List<UnVideojuego>>

    /*
    @GET("/viviendas/mine")
    fun getMyViviendas(@Header("Authorization") auth:String): Call<List<MyViviendasItem>>

     */
}