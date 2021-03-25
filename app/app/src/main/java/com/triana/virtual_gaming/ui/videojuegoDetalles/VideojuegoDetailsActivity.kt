package com.triana.virtual_gaming.ui.videojuegoDetalles

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.triana.virtual_gaming.R

class VideojuegoDetailsActivity : AppCompatActivity() {

    lateinit var juego:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_videojuego_details)

        juego = findViewById(R.id.nom_juego_detail)

        var intent:Intent = getIntent()
        juego.text = intent.getStringExtra("token")
    }
}