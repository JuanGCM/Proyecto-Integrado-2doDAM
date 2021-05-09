package com.triana.virtual_gaming.ui.ordenador

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.triana.virtual_gaming.MainActivity
import com.triana.virtual_gaming.R
import com.triana.virtual_gaming.ui.models.JuegoDetalle
import com.triana.virtual_gaming.ui.models.Procesador
import com.triana.virtual_gaming.ui.videojuegos.VideojuegoServicio
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class OrdenadorActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    private val baseUrl = "http://10.0.2.2:9000"
    private lateinit var service: VideojuegoServicio
    lateinit var proces:Spinner
    lateinit var mems:Spinner
    lateinit var grafs:Spinner
    lateinit var select1:String
    lateinit var guardar:Button
    lateinit var volver:Button
    lateinit var titulo:EditText
    //lateinit var procesadores:MutableList<Procesador>
    private val _procesadores = MutableLiveData<List<Procesador>>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ordenador)

        var intent: Intent = getIntent()
        var token = intent.getStringExtra("token")!!

        _procesadores.value = listOf()
        val retrofitUsuario = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        service = retrofitUsuario.create(VideojuegoServicio::class.java)

        service.getProcesadores("Bearer " + token).enqueue(object : Callback<List<Procesador>> {

            override fun onResponse(call: Call<List<Procesador>>, response: Response<List<Procesador>>) {

                if(response.code() == 200){
                    Log.i("Proceso ","Todo Bien")
                    Log.i("Valor Proceso ",response.body()!![0].titulo)
                    _procesadores.value =  response.body()
                }
            }
            override fun onFailure(call: Call<List<Procesador>>, t: Throwable) {
                Log.i("ERROR",t.message.toString()+" "+t.cause.toString())
            }
        })
        val procesadores: List<Procesador> = _procesadores.value!!

        proces = findViewById(R.id.newpc_proce)
        mems = findViewById(R.id.newpc_mem)
        grafs = findViewById(R.id.newpc_graf)
        guardar = findViewById(R.id.newpc_guardar)
        volver = findViewById(R.id.newpc_volver)
        titulo = findViewById(R.id.newpc_titulo)

        val apro = ArrayAdapter(this, android.R.layout.simple_spinner_item, procesadores)
        apro.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        proces.adapter = apro
/*
        ArrayAdapter.createFromResource(
            this,
            R.array.procesadores,
            android.R.layout.simple_spinner_item
        ).also{adapter->
          adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
          proces.adapter = adapter
        }
*/
        ArrayAdapter.createFromResource(
            this,
            R.array.memorias,
            android.R.layout.simple_spinner_item
        ).also{adapter->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            mems.adapter = adapter
        }

        ArrayAdapter.createFromResource(
            this,
            R.array.graficas,
            android.R.layout.simple_spinner_item
        ).also{adapter->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            grafs.adapter = adapter
        }

        volver.setOnClickListener {
            val inte = Intent(this, MainActivity::class.java).apply {
                putExtra("token",token)
            }
            startActivity(inte)
        }

        //select1 = proces.onItemSelectedListener.toString()
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        TODO("Not yet implemented")
    }
}