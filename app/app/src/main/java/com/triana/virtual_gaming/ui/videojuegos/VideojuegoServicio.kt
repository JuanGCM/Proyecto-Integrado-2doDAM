package com.triana.virtual_gaming.ui.videojuegos

import com.triana.virtual_gaming.ui.login.User
import com.triana.virtual_gaming.ui.models.DtoUserEdit
import com.triana.virtual_gaming.ui.models.JuegoDetalle
import com.triana.virtual_gaming.ui.models.Procesador
import com.triana.virtual_gaming.ui.models.UnVideojuego
import retrofit2.Call
import retrofit2.http.*

interface VideojuegoServicio{

    @GET("/videojuegos")
    fun getvideojuegos(): Call<List<JuegoDetalle>>

    @GET("/user/{username}")
    fun getUsuario(@Path("username") username:String):Call<User>

    @PUT("/user")
    fun modifyUsuario(@Header("Authorization") auth:String,@Body user: DtoUserEdit):Call<User>

    @GET("/procesadores")
    fun getProcesadores(@Header("Authorization") token: String):Call<List<Procesador>>
    /*
    @GET("/viviendas/mine")
    fun getMyViviendas(@Header("Authorization") auth:String): Call<List<MyViviendasItem>>

     */
}