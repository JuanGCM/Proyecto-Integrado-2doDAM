package com.triana.virtual_gaming.ui.perfil

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.triana.virtual_gaming.R
import com.triana.virtual_gaming.ui.login.User

class MyPerfilRecycleViewAdapter(
    private var values: User
) :RecyclerView.Adapter<MyPerfilRecycleViewAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_videojuego, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values
        holder.nombreCompleto.text = item.nombreCompleto
        holder.username.text = item.username
        holder.email.text = item.email
        holder.fechaNaci.text = item.fechaNacimiento.toString()
    }

    override fun getItemCount(): Int = values.id.toInt()

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nombreCompleto: TextView = view.findViewById(R.id.nombre_completo)
        val username: TextView = view.findViewById(R.id.usuario)
        val email: TextView = view.findViewById(R.id.correo)
        val fechaNaci: TextView = view.findViewById(R.id.fecha_naci)
    }

    fun setData(usunuevo: User) {
        this.values = usunuevo
        notifyDataSetChanged()
    }
}