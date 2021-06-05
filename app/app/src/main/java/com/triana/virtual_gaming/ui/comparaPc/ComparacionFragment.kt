package com.triana.virtual_gaming.ui.comparaPc

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.triana.virtual_gaming.MainActivity
import com.triana.virtual_gaming.R
import com.triana.virtual_gaming.ui.models.Ordenador
import com.triana.virtual_gaming.ui.videojuegos.VideojuegoServicio
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ComparacionFragment : Fragment() {


    private val baseUrl = "http://10.0.2.2:9000"
    private lateinit var service: VideojuegoServicio

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_comparacion_list, container, false)

        val retrofitVideojuegos = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        service = retrofitVideojuegos.create(VideojuegoServicio::class.java)

        service.getOrdenadores("Bearer " + MainActivity.token).enqueue(object : Callback<List<Ordenador>> {

            override fun onResponse(call: Call<List<Ordenador>>, response: Response<List<Ordenador>>) {

                if(response.code() == 200){
                    Log.i("Proceso,ordenadores","Hace la petición")
                    var listaAdapter = ComparaViewAdapter(response.body()!!, ComparaActivity.procesador, ComparaActivity.memoria, ComparaActivity.grafica)
                    val v = view as RecyclerView
                    v.layoutManager = LinearLayoutManager(context)
                    v.adapter = listaAdapter
                }
            }

            override fun onFailure(call: Call<List<Ordenador>>, t: Throwable) {
                Log.i("ERROR",t.message.toString()+" "+t.cause.toString())
            }
        })

        return view
    }
}