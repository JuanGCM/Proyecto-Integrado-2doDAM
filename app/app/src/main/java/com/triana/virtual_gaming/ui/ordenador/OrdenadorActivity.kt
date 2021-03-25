package com.triana.virtual_gaming.ui.ordenador

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import com.triana.virtual_gaming.MainActivity
import com.triana.virtual_gaming.R
import com.triana.virtual_gaming.ui.models.Procesador

class OrdenadorActivity : AppCompatActivity() {

    lateinit var proces:Spinner
    lateinit var mems:Spinner
    lateinit var grafs:Spinner
    lateinit var select1:String
    lateinit var bot_ord:Button
    lateinit var titulo:EditText
    var lista:List<Procesador> = listOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ordenador)

        proces = findViewById(R.id.procesadores)
        mems = findViewById(R.id.memorias)
        grafs = findViewById(R.id.graficas)
        bot_ord = findViewById(R.id.envia_ordenador)
        titulo = findViewById(R.id.titulo_ordenador)

        ArrayAdapter.createFromResource(
            this,
            R.array.procesadores,
            android.R.layout.simple_spinner_item
        ).also{adapter->
          adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
          proces.adapter = adapter
        }
        ArrayAdapter.createFromResource(
            this,
            R.array.memorias,
            android.R.layout.simple_spinner_item
        ).also{adapter->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            mems.adapter = adapter
        }

        ArrayAdapter.createFromResource(
            this,
            R.array.graficas,
            android.R.layout.simple_spinner_item
        ).also{adapter->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            grafs.adapter = adapter
        }

        bot_ord.setOnClickListener {
            titulo.text.clear()
        }

        //select1 = proces.onItemSelectedListener.toString()
    }
}