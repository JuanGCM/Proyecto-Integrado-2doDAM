package com.triana.virtual_gaming.ui.ordenadores

import android.content.Intent
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.triana.virtual_gaming.ui.models.Ordenador
import com.triana.virtual_gaming.ui.videojuegos.VideojuegoServicio
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class OrdenadoresViewModel() : ViewModel() {
    private val _ordenadores = MutableLiveData<List<Ordenador>>()
    private val baseUrl = "http://10.0.2.2:9000"
    private var service: VideojuegoServicio
    lateinit var token:String

    val ordenadores: LiveData<List<Ordenador>>
        get() = _ordenadores

    init {
        _ordenadores.value = listOf()

        val retrofitVideojuegos = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        service = retrofitVideojuegos.create(VideojuegoServicio::class.java)
        getOrdenadoresList()
    }

    fun getOrdenadoresList(){

        service.getOrdenadores("Bearer " + token).enqueue(object : Callback<List<Ordenador>> {

            override fun onResponse(call: Call<List<Ordenador>>, response: Response<List<Ordenador>>) {

                if(response.code() == 200){
                    Log.i("Proceso,ordenadores","Hace la petición")
                    _ordenadores.value = response.body()
                }
            }

            override fun onFailure(call: Call<List<Ordenador>>, t: Throwable) {
                Log.i("ERROR",t.message.toString()+" "+t.cause.toString())
            }
        })
    }
}