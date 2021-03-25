package com.triana.virtual_gaming

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.triana.virtual_gaming.ui.registro.RegisterActivity
import com.triana.virtual_gaming.ui.videojuegoDetalles.VideojuegoDetailsActivity

class MainActivity : AppCompatActivity() {

    lateinit var pc: FloatingActionButton
    lateinit var token:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.new_activity_main)

        val navegacion:BottomNavigationView = findViewById(R.id.nueva_navegacion)

        navegacion.background = null
        navegacion.menu.getItem(2).isEnabled = false


        var intent:Intent = getIntent()
        token = intent.getStringExtra("token")!!

        pc = findViewById(R.id.pc)

        pc.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, VideojuegoDetailsActivity::class.java).apply {
                putExtra("token", token)
            }
            startActivity(intent)
        })


        //val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(setOf(R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications))

        setupActionBarWithNavController(navController, appBarConfiguration)
        navegacion.setupWithNavController(navController)

    }
}