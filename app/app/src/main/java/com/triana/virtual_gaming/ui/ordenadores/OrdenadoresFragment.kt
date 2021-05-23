package com.triana.virtual_gaming.ui.ordenadores

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.triana.virtual_gaming.MainActivity
import com.triana.virtual_gaming.R
import com.triana.virtual_gaming.ui.models.Ordenador
import com.triana.virtual_gaming.ui.videojuegoDetails.JuegoDetallesFragment
import com.triana.virtual_gaming.ui.videojuegos.VideojuegoServicio
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class OrdenadoresFragment : Fragment() {

    lateinit var listaAdapter: MyItemRecyclerViewAdapter
    lateinit var token:String
    private val baseUrl = "http://10.0.2.2:9000"
    private lateinit var service: VideojuegoServicio


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_ordenadores_list, container, false)

        val retrofitVideojuegos = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        service = retrofitVideojuegos.create(VideojuegoServicio::class.java)

        service.getOrdenadores("Bearer " + MainActivity.token).enqueue(object : Callback<List<Ordenador>> {

            override fun onResponse(call: Call<List<Ordenador>>, response: Response<List<Ordenador>>) {

                if(response.code() == 200){
                    Log.i("Proceso,ordenadores","Hace la petición")
                    listaAdapter = MyItemRecyclerViewAdapter(activity as Context, response.body()!!,MainActivity.token,MainActivity.username,MainActivity.idusu)
                    val v = view as RecyclerView
                    v.layoutManager = LinearLayoutManager(context)
                    v.adapter= listaAdapter
                }
            }

            override fun onFailure(call: Call<List<Ordenador>>, t: Throwable) {
                Log.i("ERROR",t.message.toString()+" "+t.cause.toString())
            }
        })

        return view
    }
}