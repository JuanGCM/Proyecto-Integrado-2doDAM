package com.triana.virtual_gaming

import android.content.ClipData
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.triana.virtual_gaming.ui.login.LoginActivity
import com.triana.virtual_gaming.ui.miPerfil.MiPerfilActivity
import com.triana.virtual_gaming.ui.ordenador.OrdenadorActivity


class MainActivity : AppCompatActivity() {

    lateinit var pc: FloatingActionButton
    lateinit var token:String
    lateinit var username:String
    lateinit var idusu:String
    lateinit var perfilBot:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.new_activity_main)

        val navegacion:BottomNavigationView = findViewById(R.id.nueva_navegacion)

        navegacion.background = null
        navegacion.menu.getItem(1).isEnabled = false
        navegacion.menu.getItem(2).isEnabled = false

        var intent:Intent = getIntent()
        token = intent.getStringExtra("token")!!
        username = intent.getStringExtra("username")!!
        idusu = intent.getStringExtra("idusu")!!

        perfilBot = findViewById(R.id.ver_perfil)

        perfilBot.setOnClickListener {
            val inte = Intent(this, MiPerfilActivity::class.java).apply {
                putExtra("username",username)
                putExtra("idusu",idusu)
                putExtra("token",token)
            }
            startActivity(inte)
        }

        pc = findViewById(R.id.pc)

        pc.setOnClickListener{
            val intent = Intent(this, OrdenadorActivity::class.java).apply {
                //*CAMBIOS>
                putExtra("token", token)
                //*CAMBIOS<
            }
            startActivity(intent)
        }

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(setOf(R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications))

        setupActionBarWithNavController(navController, appBarConfiguration)
        navegacion.setupWithNavController(navController)

    }
}