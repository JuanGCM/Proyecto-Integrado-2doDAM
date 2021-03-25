package com.triana.virtual_gaming.ui.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.triana.virtual_gaming.ui.login.LoginRequest
import com.triana.virtual_gaming.ui.login.LoginResponse
import com.triana.virtual_gaming.ui.login.LoginService
import com.triana.virtual_gaming.MainActivity

import com.triana.virtual_gaming.R
import com.triana.virtual_gaming.ui.perfil.PerfilViewModel
import com.triana.virtual_gaming.ui.registro.RegisterActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LoginActivity : AppCompatActivity() {


    //private lateinit var loginViewModel: LoginViewModel
    lateinit var retrofit: Retrofit
    lateinit var service: LoginService
    var context  = this
    val baseUrl = "http://10.0.2.2:9000"

    lateinit var username: EditText
    lateinit var password: EditText
    lateinit var login: Button
    lateinit var toregister: Button
    lateinit var auth:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        username = findViewById(R.id.username)
        password = findViewById(R.id.password)
        login = findViewById(R.id.tologin)
        toregister = findViewById(R.id.toregister)

        supportActionBar?.hide()

        retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        service = retrofit.create(LoginService::class.java)

        login.setOnClickListener(View.OnClickListener {
            auth = doLogin()
        })
/*
        val bundle = Bundle()
        bundle.putString("token", auth)
        intent = Intent(this@LoginActivity,PerfilViewModel::class.java)
        intent.putExtras(bundle)
        startActivity(intent)

 */

        toregister.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, RegisterActivity::class.java).apply {
                //putExtra(EXTRA_MESSAGE, message)
            }
            startActivity(intent)
        })

    }

    fun doLogin(): String {
        Log.i("Entrada","Entra al login")

        var auth = ""

        val loginData = LoginRequest(username.text.toString(), password.text.toString())

        service.login(loginData).enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                Log.i("Entrada","Hace la petición")
                if(response.code() == 201 || response.code() == 200) {
                    //TODO Guardar el token
                    Log.i("Token", response.body()?.token.toString())
                    val sharedPref =
                        context.getSharedPreferences("FILE_PREFERENCES", Context.MODE_PRIVATE)//---------------------
                    with(sharedPref.edit()) {
                        putString("TOKEN", response.body()?.token)
                        commit()
                    }
                    Log.i("Token",sharedPref.getString("TOKEN","No existe").toString())
                    Toast.makeText(context,"Login con éxito",Toast.LENGTH_SHORT).show();
                    val intentToViviendas = Intent(context, MainActivity::class.java).apply {
                        putExtra("token", response.body()?.token.toString())
                    }
                    startActivity(intentToViviendas)

                } else {
                    Log.i("FalloLogin", "Login incorrecto")
                    Toast.makeText(context,"Datos no válidos",Toast.LENGTH_SHORT).show();
                }
                auth = response.body()?.token.toString()
            }
            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Log.i("MIGUEL", "Error")
                Log.i("MIGUEL", t.message.toString())
            }
        })

        return auth
    }
}

