package com.triana.virtual_gaming.ui.OrdenadorDetalle

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.triana.virtual_gaming.MainActivity
import com.triana.virtual_gaming.R
import com.triana.virtual_gaming.ui.editaOrdenador.EditaOrdenadorActivity
import com.triana.virtual_gaming.ui.models.Ordenador
import com.triana.virtual_gaming.ui.videojuegos.VideojuegoServicio
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class OrdenadorDetalleActivity : AppCompatActivity() {

    lateinit var tit: TextView
    lateinit var proc: TextView
    lateinit var mem: TextView
    lateinit var graf: TextView
    lateinit var editar:Button
    lateinit var eliminar:Button
    lateinit var volver:Button
    private val baseUrl = "http://10.0.2.2:9000"
    private lateinit var service: VideojuegoServicio

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ordenador_detalle)

        tit = findViewById(R.id.det_titulo)
        proc = findViewById(R.id.det_proce)
        mem = findViewById(R.id.det_memo)
        graf = findViewById(R.id.det_graf)
        editar = findViewById(R.id.det_editar)
        eliminar = findViewById(R.id.det_eliminar)
        volver = findViewById(R.id.det_volver)

        var intent: Intent = getIntent()
        var titulo =  intent.getStringExtra("titulo")
        var procesador = intent.getStringExtra("procesador")
        var ram = intent.getStringExtra("ram")
        var grafica = intent.getStringExtra("grafica")
        tit.text = titulo
        proc.text = procesador
        mem.text = ram
        graf.text = grafica
        var proce_code = intent.getStringExtra("procesadorcode")
        var ram_code = intent.getStringExtra("ramcode")
        var graf_code = intent.getStringExtra("graficacode")
        var token = intent.getStringExtra("token")!!
        var username = intent.getStringExtra("username")!!
        var idusu = intent.getStringExtra("idusu")!!
        var id:Long = intent.getStringExtra("id")!!.toLong()

        eliminar.setOnClickListener {
            eliminarOrdenador(id)
            val inte = Intent(this, MainActivity::class.java).apply {
                putExtra("token",token)
                putExtra("username",username)
                putExtra("idusu",idusu)
            }
            startActivity(inte)
        }

        editar.setOnClickListener {
            val inte = Intent(this, EditaOrdenadorActivity::class.java).apply {
                putExtra("titulo", tit.text.toString())
                putExtra("token",token)
                putExtra("username",username)
                putExtra("idusu",idusu)
                putExtra("procesadorcode",proce_code)
                putExtra("ramcode",ram_code)
                putExtra("graficacode",graf_code)
                putExtra("titulo",titulo)
                putExtra("procesador",procesador)
                putExtra("ram",ram)
                putExtra("grafica",grafica)
                putExtra("id",id.toString())
            }
            startActivity(inte)
        }

        volver.setOnClickListener {
            val inte = Intent(this, MainActivity::class.java).apply {
                putExtra("token",token)
                putExtra("username",username)
                putExtra("idusu",idusu)
            }
            startActivity(inte)
        }
    }
    fun eliminarOrdenador(idPc:Long){
        val retrofitUsuario = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        service = retrofitUsuario.create(VideojuegoServicio::class.java)

        service.eliminarOrdenador(idPc).enqueue(object : retrofit2.Callback<Ordenador> {

            override fun onResponse(call: Call<Ordenador>, response: Response<Ordenador>) {

                if(response.code() == 204){
                    Log.i("proceso", "Ordenador eliminado")
                }
            }

            override fun onFailure(call: Call<Ordenador>, t: Throwable) {
                Log.i("ERROR",t.message.toString()+" "+t.cause.toString())
            }
        })

    }
}