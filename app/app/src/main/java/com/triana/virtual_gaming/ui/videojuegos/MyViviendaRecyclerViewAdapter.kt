package com.example.realestate.ui.viviendas

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.realestate.R

import com.example.realestate.dummy.DummyContent.DummyItem
import com.example.realestate.ui.UnaVivienda

/**
 * [RecyclerView.Adapter] that can display a [DummyItem].
 * TODO: Replace the implementation with code for your data type.
 */
class MyViviendaRecyclerViewAdapter(
    private var values: List<UnaVivienda>
) : RecyclerView.Adapter<MyViviendaRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_lvivienda, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        //holder.idView.text = item.id
        //holder.contentView.text = item.content
        holder.titulov.text = item.titulo
        holder.preciov.text = item.precio.toString()
        holder.provinciav.text = item.provincia
        holder.localidadv.text = item.localidad
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titulov : TextView = view.findViewById(R.id.titulov)
        val preciov : TextView = view.findViewById(R.id.preciov)
        val provinciav : TextView = view.findViewById(R.id.provinciav)
        val localidadv : TextView = view.findViewById(R.id.localidadv)
        //val img: ImageView = view.findViewById(R.id.content)
    }

    fun setData(newViviendas: List<UnaVivienda>) {
        this.values = newViviendas
        // Refresca la IU para que se muestren los nuevos
        // Pokemon en la lista
        notifyDataSetChanged()
    }
}