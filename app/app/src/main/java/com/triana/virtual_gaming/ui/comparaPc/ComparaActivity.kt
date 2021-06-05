package com.triana.virtual_gaming.ui.comparaPc

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.triana.virtual_gaming.MainActivity
import com.triana.virtual_gaming.R
import com.triana.virtual_gaming.ui.miPerfil.MiPerfilActivity
import com.triana.virtual_gaming.ui.videojuegoDetalles.VideojuegoDetailsActivity

class ComparaActivity : AppCompatActivity() {

    lateinit var volver:Button
    companion object {
        lateinit var procesador:String
        lateinit var memoria:String
        lateinit var grafica:String
        lateinit var token:String
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_compara)

        var intent:Intent = getIntent()
        var nombre = intent.getStringExtra("nombre")
        var descripcion = intent.getStringExtra("descripcion")
        var plataforma = intent.getStringExtra("plataforma")
        var precio = intent.getStringExtra("precio")
        procesador = intent.getStringExtra("procesador")!!
        memoria = intent.getStringExtra("memoria")!!
        grafica = intent.getStringExtra("grafica")!!
        var imagen = intent.getStringExtra("imagen")
        token = intent.getStringExtra("token")!!
        var username = intent.getStringExtra("username")
        var idusu = intent.getStringExtra("idusu")
        var idjuego = intent.getStringExtra("idjuego")

        volver = findViewById(R.id.comp_volver)

        volver.setOnClickListener {
            val inte = Intent(this, VideojuegoDetailsActivity::class.java).apply {
                putExtra("nombre",nombre)
                putExtra("descripcion",descripcion)
                putExtra("plataforma",plataforma)
                putExtra("precio",precio)
                putExtra("procesador",procesador)
                putExtra("memoria",memoria)
                putExtra("grafica",grafica)
                putExtra("imagen", imagen)
                putExtra("token",token)
                putExtra("username",username)
                putExtra("idusu",idusu)
                putExtra("idjuego",idjuego)
            }
            startActivity(inte)
        }
    }
}