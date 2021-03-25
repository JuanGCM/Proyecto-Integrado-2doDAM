package com.triana.virtual_gaming.ui.perfil

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.triana.virtual_gaming.ui.login.User
import com.triana.virtual_gaming.ui.videojuegos.VideojuegoServicio
import retrofit2.Retrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.converter.gson.GsonConverterFactory

class PerfilViewModel : ViewModel() {
    // TODO: Implement the ViewModel

    private val _usuario = MutableLiveData<User>()
    private val baseUrl = "http://10.0.2.2:9000"
    private lateinit var service: VideojuegoServicio



    val usu: LiveData<User>
        get() = _usuario

    init {
        _usuario.value

        val retrofitUsuario = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        service = retrofitUsuario.create(VideojuegoServicio::class.java)
        getUser("")
    }

    fun getUser(token:String){

        service.getUsuario(token).enqueue(object :Callback<User>{

            override fun onResponse(call: Call<User>, response: Response<User>) {

                if(response.code() == 200){
                    Log.i("Entrada","Hace la petición")
                    _usuario.value = response.body()
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                Log.i("ERROR","Este es el error: "+t.message.toString()+", "+t.cause.toString())
            }
        })

    }
}