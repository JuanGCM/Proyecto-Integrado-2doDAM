package com.triana.virtual_gaming.ui.videojuegos

import com.triana.virtual_gaming.ui.login.User
import com.triana.virtual_gaming.ui.models.*
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

    @GET("/videojuegos/favs")
    fun getJuegosFav(@Header("Authorization") auth:String):Call<List<JuegoDetalle>>

    @POST("/videojuegos/favs/{id}")
    fun addFavs(@Path("id") id:Long,@Header("Authorization") token: String):Call<JuegoDetalle>

    @DELETE("/videojuegos/favs/{id}")
    fun deleteFav(@Path("id") id:Long,@Header("Authorization") token: String):Call<JuegoDetalle>

    @POST("/ordenadores/{pc}")
    fun createOrdenador(@Path("pc") pc:String, @Header("Authorization") token: String):Call<Ordenador>

    @GET("/ordenadores")
    fun getOrdenadores(@Header("Authorization") token: String):Call<List<Ordenador>>

    @PUT("/ordenadores/{pc}")
    fun modifyOrdenador(@Path("pc") pc:String):Call<Ordenador>

    @DELETE("/ordenadores/{id}")
    fun eliminarOrdenador(@Path("id")id:Long):Call<Ordenador>
    /*
    @GET("/viviendas/mine")
    fun getMyViviendas(@Header("Authorization") auth:String): Call<List<MyViviendasItem>>

     */
}