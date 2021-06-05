package com.triana.virtual_gaming.ui.videojuegos

import android.content.Context
import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import com.bumptech.glide.Glide
import com.triana.virtual_gaming.MainActivity
import com.triana.virtual_gaming.R
import com.triana.virtual_gaming.ui.models.JuegoDetalle
import com.triana.virtual_gaming.ui.models.UnVideojuego
import com.triana.virtual_gaming.ui.videojuegoDetalles.VideojuegoDetailsActivity

import com.triana.virtual_gaming.ui.videojuegos.dummy.DummyContent.DummyItem

/**
 * [RecyclerView.Adapter] that can display a [DummyItem].
 * TODO: Replace the implementation with code for your data type.
 */
class MyVideojuegosRecyclerViewAdapter(
    val ctx:Context,
    private var values: List<JuegoDetalle>
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
        Glide.with(ctx).load(item.img).into(holder.imagen)

        holder.itemView.setOnClickListener {
            val intent = Intent(ctx, VideojuegoDetailsActivity::class.java)
            intent.putExtra("nombre", item.nombre)
            intent.putExtra("descripcion", item.descripcion)
            intent.putExtra("precio", item.precio.toString()+" €")
            intent.putExtra("plataforma", item.plataforma)
            intent.putExtra("procesador", item.minProcesador.titulo)
            intent.putExtra("memoria", item.minMemoriaRAM.titulo)
            intent.putExtra("grafica", item.minTarjetaGrafica.titulo)
            intent.putExtra("imagen", item.img)
            intent.putExtra("idjuego", item.id.toString())
            intent.putExtra("token", MainActivity.token)
            intent.putExtra("username", MainActivity.username)
            intent.putExtra("idusu", MainActivity.idusu)
            ctx.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nombre: TextView = view.findViewById(R.id.nombre)
        val precio: TextView = view.findViewById(R.id.precio)
        val imagen: ImageView = view.findViewById(R.id.imagenVideojuego)
    }

    fun setData(newVideojuego: List<JuegoDetalle>) {
        this.values = newVideojuego
        notifyDataSetChanged()
    }
}