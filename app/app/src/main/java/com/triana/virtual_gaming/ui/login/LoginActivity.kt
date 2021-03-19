package com.example.realestate.ui.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.realestate.MainActivity

import com.example.realestate.R
import com.example.realestate.ui.registro.RegisterActivity
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
            doLogin()
        })

        toregister.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, RegisterActivity::class.java).apply {
                //putExtra(EXTRA_MESSAGE, message)
            }
            startActivity(intent)
        })



    }

    fun doLogin() {
        Log.i("Entrada","Entra al login")

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
                        //putExtra(EXTRA_MESSAGE, message)
                    }
                    startActivity(intentToViviendas)

                } else {
                    Log.i("FalloLogin", "Login incorrecto")
                    Toast.makeText(context,"Datos no válidos",Toast.LENGTH_SHORT).show();
                }
            }
            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Log.i("MIGUEL", "Error")
                Log.i("MIGUEL", t.message.toString())
            }
        })
    }
}
