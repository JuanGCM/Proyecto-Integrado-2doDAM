package com.triana.virtual_gaming.ui.perfil

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
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
    lateinit var usuario: User
    lateinit var perfilAdapter: MyPerfilRecycleViewAdapter

    companion object {
        fun newInstance() = PerfilFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.perfil_fragment, container, false)

        perfilViewModel =
            ViewModelProvider(this).get(PerfilViewModel::class.java)

        val v = view as RecyclerView

        perfilAdapter = MyPerfilRecycleViewAdapter(usuario)
        v.layoutManager = LinearLayoutManager(context)
        v.adapter= perfilAdapter

        perfilViewModel.usu.observe(viewLifecycleOwner, Observer {
                usua -> usuario = usua
            perfilAdapter.setData(usua)
        })

        return view
    }
}