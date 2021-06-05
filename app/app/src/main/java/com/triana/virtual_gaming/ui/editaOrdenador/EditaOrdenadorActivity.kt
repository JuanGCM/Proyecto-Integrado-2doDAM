package com.triana.virtual_gaming.ui.editaOrdenador

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import com.triana.virtual_gaming.MainActivity
import com.triana.virtual_gaming.R
import com.triana.virtual_gaming.ui.OrdenadorDetalle.OrdenadorDetalleActivity
import com.triana.virtual_gaming.ui.models.Ordenador
import com.triana.virtual_gaming.ui.videojuegos.VideojuegoServicio
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class EditaOrdenadorActivity : AppCompatActivity() {

    lateinit var tit: EditText
    lateinit var proc: Spinner
    lateinit var mem: Spinner
    lateinit var graf: Spinner
    lateinit var guardar: Button
    lateinit var volver: Button
    private val baseUrl = "http://10.0.2.2:9000"
    private lateinit var service: VideojuegoServicio

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edita_ordenador)

        tit = findViewById(R.id.editOrde_titulo)
        proc = findViewById(R.id.editOrde_proce)
        mem = findViewById(R.id.editOrde_mem)
        graf = findViewById(R.id.editOrde_graf)
        guardar = findViewById(R.id.editOrde_guardar)
        volver = findViewById(R.id.editOrde_volver)

        val procesadores: List<String> = listOf("Intel core 2 duo","Intel core i3","AMD Ryzen 3","Intel core i5","AMD Ryzen 5","Intel core i7","AMD Ryzen 7","Intel core i9","AMD Ryzen 9")
        val memorias: List<String> = listOf("6GB DDR2","6GB DDR3","8GB DDR3","8GB DDR4","12GB DDR3","12GB DDR4","16GB DDR3","16GB DDR4","24GB DDR4","24GB DDR5")
        val graficas: List<String> = listOf("NVIDIA GTX 750 ti","NVIDIA GTX 780","NVIDIA GTX 1050 ti","NVIDIA GTX 1080","NVIDIA RTX 2060","NVIDIA RTX 2070","NVIDIA TITAN V","NVIDIA TITAN X","NVIDIA RTX 3260 ti","NVIDIA RTX 3070","NVIDIA RTX 3080")

        var intent: Intent = getIntent()
        tit.setText(intent.getStringExtra("titulo"))
        var token = intent.getStringExtra("token")!!
        var username = intent.getStringExtra("username")!!
        var idusu = intent.getStringExtra("idusu")!!
        var proce_code = intent.getStringExtra("procesadorcode")
        var ram_code = intent.getStringExtra("ramcode")
        var graf_code = intent.getStringExtra("graficacode")
        var titulo =  intent.getStringExtra("titulo")
        var procesador = intent.getStringExtra("procesador")
        var ram = intent.getStringExtra("ram")
        var grafica = intent.getStringExtra("grafica")
        var id = intent.getStringExtra("id")

        val apro = ArrayAdapter(this, android.R.layout.simple_spinner_item, procesadores)
        apro.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        proc.adapter = apro

        proc.setSelection(proce_code!!.toInt() - 1)

        val amem = ArrayAdapter(this, android.R.layout.simple_spinner_item, memorias)
        amem.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        mem.adapter = amem

        mem.setSelection(ram_code!!.toInt() - 1)

        val agra = ArrayAdapter(this, android.R.layout.simple_spinner_item, graficas)
        agra.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        graf.adapter = agra

        graf.setSelection(graf_code!!.toInt() - 1)

        volver.setOnClickListener {
            val inte = Intent(this, OrdenadorDetalleActivity::class.java).apply {
                putExtra("token",token)
                putExtra("username",username)
                putExtra("idusu",idusu)
                putExtra("procesadorcode",proce_code)
                putExtra("ramcode",ram_code)
                putExtra("graficacode",graf_code)
                putExtra("titulo",titulo)
                putExtra("procesador",procesador)
                putExtra("ram",ram)
                putExtra("grafica",grafica)
                putExtra("id",id)
            }
            startActivity(inte)
        }

        guardar.setOnClickListener {
            var title:String = tit.text.toString()
            var procePosi:String = (proc.selectedItemPosition+1).toString()
            var memoPosi:String = (mem.selectedItemPosition+1).toString()
            var grafPosi:String = (graf.selectedItemPosition+1).toString()

            var ordenador = "$id,$title,$procePosi,$memoPosi,$grafPosi"

            val retrofitUsuario = Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            service = retrofitUsuario.create(VideojuegoServicio::class.java)

            editaOrdenador(ordenador)
            val inte = Intent(this, OrdenadorDetalleActivity::class.java).apply {
                putExtra("token",token)
                putExtra("username",username)
                putExtra("idusu",idusu)
                putExtra("procesadorcode",proce_code)
                putExtra("ramcode",ram_code)
                putExtra("graficacode",graf_code)
                putExtra("titulo", tit.text.toString())
                putExtra("procesador",proc.selectedItem.toString())
                putExtra("ram",mem.selectedItem.toString())
                putExtra("grafica",graf.selectedItem.toString())
                putExtra("id",id)
            }
            startActivity(inte)
        }
    }
    fun editaOrdenador(pc:String){
        service.modifyOrdenador(pc).enqueue(object : retrofit2.Callback<Ordenador> {

            override fun onResponse(call: Call<Ordenador>, response: Response<Ordenador>) {

                if(response.code() == 200){
                    Log.i("proceso", "Ordenador modificado")
                }
            }

            override fun onFailure(call: Call<Ordenador>, t: Throwable) {
                Log.i("ERROR",t.message.toString()+" "+t.cause.toString())
            }
        })
    }
}