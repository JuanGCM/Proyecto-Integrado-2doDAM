package com.triana.virtual_gaming.ui.videojuegos

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import androidx.lifecycle.Observer
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.lifecycle.ViewModelProvider
import com.triana.virtual_gaming.R
import com.triana.virtual_gaming.ui.videojuegoDetalles.VideojuegoDetailsActivity


class VideojuegosFragment : Fragment() {

    private lateinit var videojuegosViewModel: VideojuegosViewModel
    var listaVideojuegos:List<UnVideojuego> = listOf()
    lateinit var listaAdapter: MyVideojuegosRecyclerViewAdapter



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_videojuego_list, container, false)

        videojuegosViewModel =
                ViewModelProvider(this).get(VideojuegosViewModel::class.java)

        val v = view as RecyclerView

        listaAdapter = MyVideojuegosRecyclerViewAdapter(listaVideojuegos)
        v.layoutManager = LinearLayoutManager(context)
        v.adapter= listaAdapter

        videojuegosViewModel.juegos.observe(viewLifecycleOwner, Observer {
            games -> listaVideojuegos = games
            listaAdapter.setData(games.sortedWith(compareBy({ it.id })))
        })

        var listado:RecyclerView = view.findViewById(R.id.listado)
        listado.setOnClickListener {
            view -> openVideojuegoDetail()

        }

        return view
    }

    fun openVideojuegoDetail(){
        //var intent:Intent = Intent(this,VideojuegoDetailsActivity::class.java)
        //startActivity(intent)
    }

}