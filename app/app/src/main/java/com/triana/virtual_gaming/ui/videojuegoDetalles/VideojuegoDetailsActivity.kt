package com.triana.virtual_gaming.ui.videojuegoDetalles

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.triana.virtual_gaming.MainActivity
import com.triana.virtual_gaming.R
import com.triana.virtual_gaming.ui.comparaPc.ComparaActivity
import com.triana.virtual_gaming.ui.models.JuegoDetalle
import com.triana.virtual_gaming.ui.videojuegos.VideojuegoServicio
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class VideojuegoDetailsActivity : AppCompatActivity() {

    lateinit var nom:TextView
    lateinit var desc:TextView
    lateinit var prec:TextView
    lateinit var plat:TextView
    lateinit var proc:TextView
    lateinit var mem:TextView
    lateinit var graf:TextView
    lateinit var volver:Button
    lateinit var comparar:Button
    lateinit var megusta:Button
    lateinit var nomegusta:Button
    lateinit var juegoimg:ImageView
    private val baseUrl = "http://10.0.2.2:9000"
    private lateinit var service: VideojuegoServicio

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_videojuego_details)

        nom = findViewById(R.id.nom_juego_detail)
        desc = findViewById(R.id.descrip_juego_detail)
        plat = findViewById(R.id.plat_juego_detail)
        prec = findViewById(R.id.precio_juego_detail)
        proc = findViewById(R.id.proce_detalle)
        mem = findViewById(R.id.ram_detalle)
        graf = findViewById(R.id.graf_detalle)
        volver = findViewById(R.id.descrip_juego_volver)
        comparar = findViewById(R.id.descrip_juego_comparar)
        megusta = findViewById(R.id.megusta)
        nomegusta = findViewById(R.id.nomegusta)

        juegoimg = findViewById(R.id.juego_img)

        var intent:Intent = getIntent()
        var nombre = intent.getStringExtra("nombre")
        var descripcion = intent.getStringExtra("descripcion")
        var plataforma = intent.getStringExtra("plataforma")
        var precio = intent.getStringExtra("precio")
        var procesador = intent.getStringExtra("procesador")
        var memoria = intent.getStringExtra("memoria")
        var grafica = intent.getStringExtra("grafica")
        var imagen = intent.getStringExtra("imagen")
        var token = intent.getStringExtra("token")
        var username = intent.getStringExtra("username")
        var idusu = intent.getStringExtra("idusu")
        var idjuego = intent.getStringExtra("idjuego")!!.toLong()
        nom.text = nombre
        desc.text = descripcion
        plat.text = plataforma
        prec.text = precio
        proc.text = procesador
        mem.text = memoria
        graf.text = grafica
        Glide.with(this).load(imagen).into(juegoimg)

        volver.setOnClickListener {
            val inte = Intent(this, MainActivity::class.java).apply {
                putExtra("token",token)
                putExtra("username",username)
                putExtra("idusu",idusu)
            }
            startActivity(inte)
        }

        megusta.setOnClickListener {
            val retrofitVideojuegos = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            service = retrofitVideojuegos.create(VideojuegoServicio::class.java)
            service.addFavs(idjuego,"Bearer " + MainActivity.token).enqueue(object :
                Callback<JuegoDetalle> {

                override fun onResponse(call: Call<JuegoDetalle>, response: Response<JuegoDetalle>) {

                    if(response.code() == 200){
                        Log.i("Proceso,juegos fav","Agrega a favoritos con exito")
                    }
                }

                override fun onFailure(call: Call<JuegoDetalle>, t: Throwable) {
                    Log.i("ERROR",t.message.toString()+" "+t.cause.toString())
                }
            })
        }

        nomegusta.setOnClickListener {
            val retrofitVideojuegos = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            service = retrofitVideojuegos.create(VideojuegoServicio::class.java)
            service.deleteFav(idjuego,"Bearer " + MainActivity.token).enqueue(object :
                Callback<JuegoDetalle> {

                override fun onResponse(call: Call<JuegoDetalle>, response: Response<JuegoDetalle>) {

                    if(response.code() == 200){
                        Log.i("Proceso,juegos fav","Elimina juego de favoritos")
                    }
                }

                override fun onFailure(call: Call<JuegoDetalle>, t: Throwable) {
                    Log.i("ERROR",t.message.toString()+" "+t.cause.toString())
                }
            })
        }

        comparar.setOnClickListener {
            val inte = Intent(this, ComparaActivity::class.java).apply {
                putExtra("nombre",nombre)
                putExtra("descripcion",descripcion)
                putExtra("plataforma",plataforma)
                putExtra("precio",precio)
                putExtra("procesador",procesador)
                putExtra("memoria",memoria)
                putExtra("grafica",grafica)
                putExtra("imagen",imagen)
                putExtra("token",token)
                putExtra("username",username)
                putExtra("idusu",idusu)
                putExtra("idjuego", idjuego.toString())
            }
            startActivity(inte)
        }

    }
}