package com.example.realestate.ui.registro

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.realestate.R
import com.example.realestate.ui.login.LoginActivity
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.text.SimpleDateFormat
import java.util.*

class RegisterActivity : AppCompatActivity() {

    //private lateinit var loginViewModel: LoginViewModel
    lateinit var retrofit: Retrofit
    lateinit var service: RegisterService
    var context  = this
    val baseUrl = "http://10.0.2.2:9000"

    lateinit var username: EditText
    lateinit var password: EditText
    lateinit var email:EditText
    lateinit var nombreCompleto:EditText
    lateinit var fechaNacimiento:EditText
    lateinit var tologin:Button
    lateinit var register: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_register)

        username = findViewById<EditText>(R.id.username)
        password = findViewById<EditText>(R.id.password)
        email = findViewById(R.id.email)
        nombreCompleto = findViewById(R.id.nombrecompleto)
        fechaNacimiento = findViewById(R.id.fechanacimiento)
        register = findViewById(R.id.register)
        tologin = findViewById<Button>(R.id.tologin)
        context = this

        fechaNacimiento.setFocusable(false)

        supportActionBar?.hide()

        retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        service = retrofit.create(RegisterService::class.java)

        register.setOnClickListener(View.OnClickListener {
            doRegister()
        })

        tologin.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, LoginActivity::class.java).apply {
                //putExtra(EXTRA_MESSAGE, message)
            }
            startActivity(intent)
        })

       var  builder = MaterialDatePicker.Builder.datePicker()
        builder.setTitleText("Fecha de nacimiento")
        val materialDatePicker = builder.build()

        fechaNacimiento.setOnClickListener {
            materialDatePicker.show(getSupportFragmentManager(),"DATE_PICKER")
        }

        materialDatePicker.addOnPositiveButtonClickListener(
            MaterialPickerOnPositiveButtonClickListener {
                val dateString =
                    //SimpleDateFormat("dd/MM/yyyy").format(Date(it))

                    SimpleDateFormat("yyyy-MM-dd").format(Date(it))

                //fechaNacimiento.setText(materialDatePicker.headerText)
                fechaNacimiento.setText(dateString)
                Log.i("TEST", dateString)
            })
    }

    fun doRegister() {
        Log.i("Entrada","Entra al login")

        val registerData = RegisterRequest(username.text.toString(), password.text.toString(), email.text.toString(),
                nombreCompleto.text.toString(), fechaNacimiento.text.toString())
        Log.i("A enviar",registerData.toString())
        service.register(registerData).enqueue(object : Callback<RegisterResponse> {
            override fun onResponse(call: Call<RegisterResponse>, response: Response<RegisterResponse>) {
                Log.i("Entrada","Hace la petición")
                if(response.code() == 201 || response.code() == 200) {
                    //Redirigir al login
                    Log.i("EXITO",response.body().toString())
                    val intent = Intent(context, LoginActivity::class.java).apply {
                    }
                    startActivity(intent)

                } else {
                    Log.i("FalloLogin", "Registro incorrecto")
                    Toast.makeText(context,"Datos no válidos",Toast.LENGTH_SHORT).show();
                }
            }


            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }




}


