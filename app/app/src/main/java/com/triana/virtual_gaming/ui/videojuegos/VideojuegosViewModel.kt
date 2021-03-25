package com.triana.virtual_gaming.ui.videojuegos

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.triana.virtual_gaming.ui.models.JuegoDetalle
import com.triana.virtual_gaming.ui.models.UnVideojuego
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class VideojuegosViewModel: ViewModel() {

    private val _juegos = MutableLiveData<List<JuegoDetalle>>()
    private val baseUrl = "http://10.0.2.2:9000"
    private lateinit var service: VideojuegoServicio

    val juegos:LiveData<List<JuegoDetalle>>
        get() = _juegos

    init {
        _juegos.value = listOf()

        val retrofitVideojuegos = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        service = retrofitVideojuegos.create(VideojuegoServicio::class.java)
        getVideojuegosList()
    }

    fun getVideojuegosList(){

        service.getvideojuegos().enqueue(object :Callback<List<JuegoDetalle>>{

            override fun onResponse(call: Call<List<JuegoDetalle>>, response: Response<List<JuegoDetalle>>) {

                if(response.code() == 200){
                    Log.i("ERROR","Hace la petición")
                    _juegos.value = response.body()
                }
            }

            override fun onFailure(call: Call<List<JuegoDetalle>>, t: Throwable) {
                Log.i("ERROR",t.message.toString()+" "+t.cause.toString())
            }
        })

    }
}