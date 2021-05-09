package com.triana.virtual_gaming.ui.EditPerfil

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.triana.virtual_gaming.MainActivity
import com.triana.virtual_gaming.R
import com.triana.virtual_gaming.ui.login.User
import com.triana.virtual_gaming.ui.miPerfil.MiPerfilActivity
import com.triana.virtual_gaming.ui.models.DtoUserEdit
import com.triana.virtual_gaming.ui.videojuegos.VideojuegoServicio
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.security.auth.callback.Callback

class EditPerfilActivity : AppCompatActivity() {

    lateinit var guardar: Button
    private lateinit var service: VideojuegoServicio
    private val baseUrl = "http://10.0.2.2:9000"
    lateinit var idusu:String
    lateinit var nomCom:String
    lateinit var fecha:String
    lateinit var email:String
    lateinit var token:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_perfil)
        var intent: Intent = getIntent()
        token = intent.getStringExtra("token")!!
        idusu = intent.getStringExtra("idusu")!!
        nomCom = intent.getStringExtra("nomCom")!!
        fecha = intent.getStringExtra("fechaNaci")!!
        email = intent.getStringExtra("email")!!
        var nomUser = intent.getStringExtra("username")!!

        guardar = findViewById(R.id.edit_guardar)

        var ncom:EditText = findViewById(R.id.edit_nombre)
        var ema :EditText = findViewById(R.id.edit_correo)
        var fec :EditText = findViewById(R.id.edit_fecha)
        ncom.setText(nomCom)
        ema.setText(email)
        fec.setText(fecha)

        guardar.setOnClickListener {
            val retrofitUsuario = Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            service = retrofitUsuario.create(VideojuegoServicio::class.java)

            var form = DtoUserEdit(ncom.text.toString(),ema.text.toString(),fec.text.toString())
            doEditProfile(form)
            val inte = Intent(this, MainActivity::class.java).apply {
                putExtra("username",nomUser)
                putExtra("idusu",idusu)
                putExtra("token",token)
            }
            startActivity(inte)
        }
    }
    fun doEditProfile(form:DtoUserEdit){
        service.modifyUsuario("Bearer " + token,form).enqueue(object : retrofit2.Callback<User> {

            override fun onResponse(call: Call<User>, response: Response<User>) {

                if(response.code() == 201){
                    Log.i("nombre", "Todo bien")
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                Log.i("ERROR",t.message.toString()+" "+t.cause.toString())
            }
        })
    }
}