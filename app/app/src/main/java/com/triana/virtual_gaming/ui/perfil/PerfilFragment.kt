package com.triana.virtual_gaming.ui.perfil

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.triana.virtual_gaming.R
import com.triana.virtual_gaming.ui.login.User
import androidx.lifecycle.Observer

class PerfilFragment : Fragment() {

    private lateinit var perfilViewModel: PerfilViewModel
    var usuario: User = User("","","","","")
    //lateinit var perfilAdapter: MyPerfilRecycleViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.perfil_fragment, container, false)

        perfilViewModel =
            ViewModelProvider(this).get(PerfilViewModel::class.java)

        //perfilAdapter = MyPerfilRecycleViewAdapter(usuario)

        perfilViewModel.usu.observe(viewLifecycleOwner, Observer {
                usua -> usuario = usua
            //perfilAdapter.setData(usua)
            Log.i("Usu",usuario.nombreCompleto)
        })



        return view
    }
}