package com.example.realestate.ui.viviendas

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.realestate.ui.UnaVivienda
import com.example.realestate.ui.ViviendaServicio
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ViviendasViewModel : ViewModel() {

    private val _viviendas = MutableLiveData<List<UnaVivienda>>()
    private val baseUrl = "http://10.0.2.2:9000"
    private lateinit var service: ViviendaServicio

    val viviendas: LiveData<List<UnaVivienda>>
        get() = _viviendas

    init {
        _viviendas.value = listOf()

         /*var gson: Gson =  GsonBuilder()
                .setLenient()
                .create();*/

        val retrofitViviendas = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        service = retrofitViviendas.create(ViviendaServicio::class.java)
        getViviendasList()
    }

    fun getViviendasList() {
        service.getViviendas().enqueue(object: Callback<List<UnaVivienda>> {
            override fun onResponse(
                    call: Call<List<UnaVivienda>>,
                    response: Response<List<UnaVivienda>>
            ) {
                if(response.code() == 200) {
                    Log.i("ERROR","Hace la petición")
                    _viviendas.value = response.body()
                }
            }

            override fun onFailure(call: Call<List<UnaVivienda>>, t: Throwable) {
                // Entra cuando falla la comunicación con el servidor
                Log.i("ERROR",t.message.toString()+" "+t.cause.toString())
            }
        })
    }
}




