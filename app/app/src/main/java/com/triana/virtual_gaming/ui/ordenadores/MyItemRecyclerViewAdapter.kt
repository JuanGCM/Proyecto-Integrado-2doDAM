package com.triana.virtual_gaming.ui.ordenadores

import android.content.Context
import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.triana.virtual_gaming.R
import com.triana.virtual_gaming.ui.OrdenadorDetalle.OrdenadorDetalleActivity
import com.triana.virtual_gaming.ui.models.JuegoDetalle
import com.triana.virtual_gaming.ui.models.Ordenador

import com.triana.virtual_gaming.ui.ordenadores.dummy.DummyContent.DummyItem
import com.triana.virtual_gaming.ui.videojuegoDetalles.VideojuegoDetailsActivity

/**
 * [RecyclerView.Adapter] that can display a [DummyItem].
 * TODO: Replace the implementation with code for your data type.
 */
class MyItemRecyclerViewAdapter(
    val ctx: Context,
    private var values: List<Ordenador>,
    val token:String,
    val username:String,
    val idusu:String
) : RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_ordenadores, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.titulo.text = item.titulo
        holder.proce.text = item.procesador.titulo
        holder.ram.text = item.ram.titulo
        holder.graf.text = item.grafica.titulo

        holder.itemView.setOnClickListener {
            val intent = Intent(ctx, OrdenadorDetalleActivity::class.java)
            intent.putExtra("titulo", item.titulo)
            intent.putExtra("procesador", item.procesador.titulo)
            intent.putExtra("procesadorcode", item.procesador.code.toString())
            intent.putExtra("ram", item.ram.titulo)
            intent.putExtra("ramcode", item.ram.code.toString())
            intent.putExtra("grafica", item.grafica.titulo)
            intent.putExtra("graficacode", item.grafica.code.toString())
            intent.putExtra("token",token)
            intent.putExtra("username",username)
            intent.putExtra("idusu",idusu)
            intent.putExtra("id",item.id.toString())
            ctx.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titulo: TextView = view.findViewById(R.id.pclist_titulo)
        val proce: TextView = view.findViewById(R.id.pclist_procesador)
        val ram: TextView = view.findViewById(R.id.pclist_ram)
        val graf: TextView = view.findViewById(R.id.pclist_grafica)

    }
}