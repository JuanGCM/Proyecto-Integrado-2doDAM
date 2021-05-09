package com.triana.virtual_gaming.ui.miPerfil

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.triana.virtual_gaming.MainActivity
import com.triana.virtual_gaming.R
import com.triana.virtual_gaming.ui.EditPerfil.EditPerfilActivity
import com.triana.virtual_gaming.ui.login.User
import com.triana.virtual_gaming.ui.videojuegos.VideojuegoServicio
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MiPerfilActivity : AppCompatActivity() {

    private val usuario = MutableLiveData<User>()
    private val baseUrl = "http://10.0.2.2:9000"
    private lateinit var service: VideojuegoServicio
    lateinit var editPerfil: Button
    lateinit var volver: Button
    lateinit var idusu:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mi_perfil)

        val retrofitUsuario = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        service = retrofitUsuario.create(VideojuegoServicio::class.java)

        var intent: Intent = getIntent()
        var token = intent.getStringExtra("token")!!
        var nomUser:String = intent.getStringExtra("username")!!
        idusu = intent.getStringExtra("idusu")!!

        var nombreCompleto: TextView = findViewById(R.id.nombre_completo)
        var username: TextView = findViewById(R.id.usuario)
        var email: TextView = findViewById(R.id.correo)
        var fechaNaci: TextView = findViewById(R.id.fecha_naci)
        editPerfil = findViewById(R.id.edit_perfil)
        volver = findViewById(R.id.edit_volver)

        editPerfil.setOnClickListener {
            val intnt = Intent(this, EditPerfilActivity::class.java).apply {
                putExtra("username",nomUser)
                putExtra("nomCom",nombreCompleto.text)
                putExtra("email",email.text)
                putExtra("fechaNaci",fechaNaci.text)
                putExtra("idusu",idusu)
                putExtra("token",token)
            }//Enviar los datos al editperfil
            startActivity(intnt)
        }
        volver.setOnClickListener {
            val inte = Intent(this, MainActivity::class.java).apply {
                putExtra("username",nomUser)
                putExtra("idusu",idusu)
                putExtra("token",token)
            }
            startActivity(inte)
        }

        service.getUsuario(nomUser).enqueue(object : Callback<User> {

            override fun onResponse(call: Call<User>, response: Response<User>) {

                if(response.code() == 201){
                    usuario.value = response.body()
                    nombreCompleto.text = response.body()!!.nombreCompleto
                    username.text = response.body()!!.username
                    email.text = response.body()!!.email
                    fechaNaci.text = response.body()!!.fechaNacimiento.split("T")[0]
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                Log.i("ERROR",t.message.toString()+" "+t.cause.toString())
            }
        })
    }

}