package com.example.realestate.ui.viviendas

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.realestate.R
import com.example.realestate.ui.UnaVivienda

class ViviendasFragment : Fragment() {

    private lateinit var viviendasViewModel: ViviendasViewModel
    var listaViviendas: List<UnaVivienda> = listOf()
    lateinit var listaAdapter: MyViviendaRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_viviendas_list/*fragment_viviendas*/, container, false)

        viviendasViewModel =
                ViewModelProvider(this).get(ViviendasViewModel::class.java)

        val v = view as RecyclerView

        listaAdapter = MyViviendaRecyclerViewAdapter(listaViviendas)
        v.layoutManager = LinearLayoutManager(context)
        v.adapter/*(listaAdapter)*/ = listaAdapter

        viviendasViewModel.viviendas.observe(viewLifecycleOwner, Observer {
            pokemons -> listaViviendas = pokemons
            listaAdapter.setData(pokemons.sortedWith(compareBy({ it.id })))
        })

        return view
    }
}
