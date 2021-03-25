package com.triana.virtual_gaming

import android.content.ClipData
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.triana.virtual_gaming.ui.login.LoginActivity
import com.triana.virtual_gaming.ui.ordenador.OrdenadorActivity


class MainActivity : AppCompatActivity() {

    lateinit var pc: FloatingActionButton
    //lateinit var token:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.new_activity_main)

        val navegacion:BottomNavigationView = findViewById(R.id.nueva_navegacion)

        navegacion.background = null
        navegacion.menu.getItem(1).isEnabled = false
        navegacion.menu.getItem(2).isEnabled = false



        //var intent:Intent = getIntent()
        //token = intent.getStringExtra("token")!!


        pc = findViewById(R.id.pc)

        pc.setOnClickListener{
            val intent = Intent(this, OrdenadorActivity::class.java).apply {
                //putExtra("token", token)
            }
            startActivity(intent)
        }


        //val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(setOf(R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications))

        setupActionBarWithNavController(navController, appBarConfiguration)
        navegacion.setupWithNavController(navController)

    }
}