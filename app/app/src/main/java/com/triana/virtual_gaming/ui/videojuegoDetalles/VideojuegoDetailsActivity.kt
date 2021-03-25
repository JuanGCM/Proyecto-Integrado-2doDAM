package com.triana.virtual_gaming.ui.videojuegoDetalles

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.triana.virtual_gaming.MainActivity
import com.triana.virtual_gaming.R
import com.triana.virtual_gaming.ui.videojuegos.MyVideojuegosRecyclerViewAdapter

class VideojuegoDetailsActivity : AppCompatActivity() {

    lateinit var nom:TextView
    lateinit var desc:TextView
    lateinit var prec:TextView
    lateinit var plat:TextView
    lateinit var proc:TextView
    lateinit var mem:TextView
    lateinit var graf:TextView

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

        var intent:Intent = getIntent()
        nom.text = intent.getStringExtra("nombre")
        desc.text = intent.getStringExtra("descripcion")
        plat.text = intent.getStringExtra("plataforma")
        prec.text = intent.getStringExtra("precio")
        proc.text = intent.getStringExtra("procesador")
        mem.text = intent.getStringExtra("memoria")
        graf.text = intent.getStringExtra("grafica")

    }
}