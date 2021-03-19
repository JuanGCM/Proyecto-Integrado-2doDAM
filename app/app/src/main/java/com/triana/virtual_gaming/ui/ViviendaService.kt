package com.example.realestate.ui

import com.example.realestate.ui.ListaViviendasResponse
import com.example.realestate.ui.UnaVivienda
import com.example.realestate.ui.propias.models.MyViviendasItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface ViviendaServicio{

    @GET("/viviendas")
    fun getViviendas(): Call<List<UnaVivienda>>

    @GET("/viviendas/mine")
    fun getMyViviendas(@Header("Authorization") auth:String): Call<List<MyViviendasItem>>

}