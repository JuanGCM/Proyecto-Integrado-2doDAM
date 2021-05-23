package com.triana.virtual_gaming.ui.ordenador

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.core.view.get
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.triana.virtual_gaming.MainActivity
import com.triana.virtual_gaming.R
import com.triana.virtual_gaming.ui.login.User
import com.triana.virtual_gaming.ui.models.Ordenador
import com.triana.virtual_gaming.ui.models.Procesador
import com.triana.virtual_gaming.ui.videojuegos.VideojuegoServicio
import com.triana.virtual_gaming.ui.videojuegos.VideojuegosViewModel
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
    lateinit var guardar:Button
    lateinit var volver:Button
    lateinit var titulo:EditText
    lateinit var token:String
    //private val _procesadores = MutableLiveData<List<Procesador>>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ordenador)

        var intent: Intent = getIntent()
        token = intent.getStringExtra("token")!!
        var username = intent.getStringExtra("username")!!
        var idusu = intent.getStringExtra("idusu")!!

        /*

        _procesadores.value = listOf()
        val retrofitUsuario = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        service = retrofitUsuario.create(VideojuegoServicio::class.java)

        service.getProcesadores("Bearer " + token).enqueue(object : Callback<List<Procesador>> {

            override fun onResponse(call: Call<List<Procesador>>, response: Response<List<Procesador>>) {

                if(response.code() == 200){
                    Log.i("Valor Proceso ",response.body()!![0].titulo)
                    _procesadores.value =  response.body()
                }
            }
            override fun onFailure(call: Call<List<Procesador>>, t: Throwable) {
                Log.i("ERROR",t.message.toString()+" "+t.cause.toString())
            }
        })

         */

        val procesadores: List<String> = listOf("Intel core 2 duo","Intel core i3","AMD Ryzen 3","Intel core i5","AMD Ryzen 5","Intel core i7","AMD Ryzen 7","Intel core i9","AMD Ryzen 9")
        val memorias: List<String> = listOf("6GB DDR2","6GB DDR3","8GB DDR3","8GB DDR4","12GB DDR3","12GB DDR4","16GB DDR3","16GB DDR4","24GB DDR4","24GB DDR5")
        val graficas: List<String> = listOf("NVIDIA GTX 750 ti","NVIDIA GTX 780","NVIDIA GTX 1050 ti","NVIDIA GTX 1080","NVIDIA RTX 2060","NVIDIA RTX 2070","NVIDIA TITAN V","NVIDIA TITAN X","NVIDIA RTX 3260 ti","NVIDIA RTX 3070","NVIDIA RTX 3080")

        proces = findViewById(R.id.newpc_proce)
        mems = findViewById(R.id.newpc_mem)
        grafs = findViewById(R.id.newpc_graf)
        guardar = findViewById(R.id.newpc_guardar)
        volver = findViewById(R.id.newpc_volver)
        titulo = findViewById(R.id.newpc_titulo)


        val apro = ArrayAdapter(this, android.R.layout.simple_spinner_item, procesadores)
        apro.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        proces.adapter = apro

        val amem = ArrayAdapter(this, android.R.layout.simple_spinner_item, memorias)
        amem.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        mems.adapter = amem

        val agra = ArrayAdapter(this, android.R.layout.simple_spinner_item, graficas)
        agra.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        grafs.adapter = agra




        volver.setOnClickListener {
            val inte = Intent(this, MainActivity::class.java).apply {
                putExtra("token",token)
                putExtra("username",username)
                putExtra("idusu",idusu)
            }
            startActivity(inte)
        }

        guardar.setOnClickListener {

            var title:String = titulo.text.toString()
            var procePosi:String = (proces.selectedItemPosition+1).toString()
            var memoPosi:String = (mems.selectedItemPosition+1).toString()
            var grafPosi:String = (grafs.selectedItemPosition+1).toString()

            var ordenador = "$title,$procePosi,$memoPosi,$grafPosi"

            val retrofitUsuario = Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            service = retrofitUsuario.create(VideojuegoServicio::class.java)
            createOrdenador(ordenador)
            val intent = Intent(this, MainActivity::class.java).apply {
                putExtra("token",token)
                putExtra("username",username)
                putExtra("idusu",idusu)
            }
            startActivity(intent)
        }
    }

    fun createOrdenador(ordenador:String){
        service.createOrdenador(ordenador,"Bearer " + token).enqueue(object : retrofit2.Callback<Ordenador> {

            override fun onResponse(call: Call<Ordenador>, response: Response<Ordenador>) {

                if(response.code() == 201){
                    Log.i("proceso", "Ordenador creado")
                }
            }

            override fun onFailure(call: Call<Ordenador>, t: Throwable) {
                Log.i("ERROR",t.message.toString()+" "+t.cause.toString())
            }
        })
    }
    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

    }
}