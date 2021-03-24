package com.triana.virtual_gaming.ui.videojuegos

import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.triana.virtual_gaming.R
import com.triana.virtual_gaming.ui.videojuegoDetalles.VideojuegoDetailsActivity

import com.triana.virtual_gaming.ui.videojuegos.dummy.DummyContent.DummyItem

/**
 * [RecyclerView.Adapter] that can display a [DummyItem].
 * TODO: Replace the implementation with code for your data type.
 */
class MyVideojuegosRecyclerViewAdapter(
    private var values: List<UnVideojuego>
) : RecyclerView.Adapter<MyVideojuegosRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_videojuego, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.nombre.text = item.nombre
        holder.precio.text = item.precio.toString()+" €"
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nombre: TextView = view.findViewById(R.id.nombre)
        val precio: TextView = view.findViewById(R.id.precio)
    }

    fun setData(newVideojuego: List<UnVideojuego>) {
        this.values = newVideojuego
        notifyDataSetChanged()
    }
}