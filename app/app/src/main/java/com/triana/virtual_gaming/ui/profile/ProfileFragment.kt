package com.triana.virtual_gaming.ui.profile

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import com.triana.virtual_gaming.R
import com.triana.virtual_gaming.ui.login.User
import androidx.lifecycle.Observer

class ProfileFragment : Fragment() {

    private lateinit var perfilViewModel: ProfileViewModel
    var usuario:MutableList<User?> = mutableListOf()

    companion object {
        fun newInstance() = ProfileFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.perfil_fragment, container, false)

        perfilViewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)
        //val v = view as RecyclerView
        //perfilAdapter = MyPerfilRecycleViewAdapter(usuario)
        //v.layoutManager = LinearLayoutManager(context)
        //v.adapter= perfilAdapter

        val nombreCompleto: TextView = view.findViewById(R.id.nombre_completo)
        val username: TextView = view.findViewById(R.id.usuario)
        val email: TextView = view.findViewById(R.id.correo)
        val fechaNaci: TextView = view.findViewById(R.id.fecha_naci)

        perfilViewModel.usu.observe(viewLifecycleOwner, Observer {
                usua -> nombreCompleto.text = usua.nombreCompleto
        })

        perfilViewModel.usu.observe(viewLifecycleOwner, Observer {
                usua -> username.text = usua.username
        })

        perfilViewModel.usu.observe(viewLifecycleOwner, Observer {
                usua -> email.text = usua.email
        })

        perfilViewModel.usu.observe(viewLifecycleOwner, Observer {
                usua -> fechaNaci.text = usua.fechaNacimiento.split("T")[0]
        })


/*
        nombreCompleto.text = usuario[0]?.nombreCompleto
        username.text = usuario[0]?.username
        email.text = usuario[0]?.email
        fechaNaci.text = usuario[0]?.fechaNacimiento


 */
        return view
    }

}