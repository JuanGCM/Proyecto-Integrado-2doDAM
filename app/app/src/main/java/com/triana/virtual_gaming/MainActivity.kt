package com.triana.virtual_gaming

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
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
    lateinit var perfilBot:Button
    lateinit var ctx: Context
    companion object {
        lateinit var token: String
        lateinit var username:String
        lateinit var idusu:String
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.new_activity_main)

        val navegacion:BottomNavigationView = findViewById(R.id.nueva_navegacion)

        navegacion.background = null
        navegacion.menu.getItem(2).isEnabled = false
        navegacion.menu.getItem(4).setOnMenuItemClickListener {
            salir()
        }

        ctx = this

        var intent:Intent = getIntent()
        token = intent.getStringExtra("token")!!
        username = intent.getStringExtra("username")!!
        idusu = intent.getStringExtra("idusu")!!

        perfilBot = findViewById(R.id.ver_perfil)

        perfilBot.setOnClickListener {
            val inte = Intent(ctx, MiPerfilActivity::class.java).apply {
                putExtra("username",username)
                putExtra("idusu",idusu)
                putExtra("token",token)
            }
            startActivity(inte)
        }

        pc = findViewById(R.id.pc)

        pc.setOnClickListener{
            val intent = Intent(ctx, OrdenadorActivity::class.java).apply {
                putExtra("token", token)
                putExtra("username",username)
                putExtra("idusu",idusu)

            }
            startActivity(intent)
        }

        val navController = findNavController(R.id.nav_host_fragment)
        val appBarConfiguration = AppBarConfiguration(setOf(R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications))

        setupActionBarWithNavController(navController, appBarConfiguration)
        navegacion.setupWithNavController(navController)
    }

    fun salir():Boolean{
        val intell = Intent(ctx, LoginActivity::class.java)
        startActivity(intell)
        return true
    }
}