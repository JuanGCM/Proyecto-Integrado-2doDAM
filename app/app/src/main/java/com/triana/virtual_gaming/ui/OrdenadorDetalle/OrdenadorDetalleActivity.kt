package com.triana.virtual_gaming.ui.OrdenadorDetalle

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.triana.virtual_gaming.MainActivity
import com.triana.virtual_gaming.R
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
    lateinit var token:String
    lateinit var username:String
    lateinit var idusu:String
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

        var intent: Intent = getIntent()
        tit.text = intent.getStringExtra("titulo")
        proc.text = intent.getStringExtra("procesador")
        mem.text = intent.getStringExtra("ram")
        graf.text = intent.getStringExtra("grafica")
        token = intent.getStringExtra("token")!!
        username = intent.getStringExtra("username")!!
        idusu = intent.getStringExtra("idusu")!!
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