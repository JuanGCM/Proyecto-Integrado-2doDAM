package com.triana.virtual_gaming.ui.comparaPc

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.triana.virtual_gaming.R
import com.triana.virtual_gaming.ui.models.Ordenador

class ComparaViewAdapter(
        private var values: List<Ordenador>,
        val procesador:String,
        val memoria:String,
        val grafica:String
) : RecyclerView.Adapter<ComparaViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_comparacion, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        if (values.isNotEmpty()) {
            val item = values[position]
            holder.pc_titulo.text = item.titulo
            holder.pc_proce.text = item.procesador.titulo
            holder.pc_ram.text = item.ram.titulo
            holder.pc_graf.text = item.grafica.titulo
            holder.req_proce.text = procesador
            holder.req_ram.text = memoria
            holder.req_graf.text = grafica
            holder.esApto.text = esApto(position)
        }
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val pc_titulo: TextView = view.findViewById(R.id.comp_titulo)
        val pc_proce: TextView = view.findViewById(R.id.comp_proce)
        val pc_ram: TextView = view.findViewById(R.id.comp_ram)
        val pc_graf: TextView = view.findViewById(R.id.comp_graf)

        val req_proce: TextView = view.findViewById(R.id.comp_req_proce)
        val req_ram: TextView = view.findViewById(R.id.comp_req_ram)
        val req_graf:TextView = view.findViewById(R.id.comp_req_graf)

        val esApto:TextView = view.findViewById(R.id.comp_esApto)
    }

    fun esApto(pos:Int):String{
        val pc = values[pos]
        var reqProce:Int = 0
        var reqGraf:Int = 0
        val procesadores: List<String> = listOf("Intel core 2 duo","Intel core i3","AMD Ryzen 3","Intel core i5","AMD Ryzen 5","Intel core i7","AMD Ryzen 7","Intel core i9","AMD Ryzen 9")
        val graficas: List<String> = listOf("NVIDIA GTX 750 ti","NVIDIA GTX 780","NVIDIA GTX 1050 ti","NVIDIA GTX 1080","NVIDIA RTX 2060","NVIDIA RTX 2070","NVIDIA TITAN V","NVIDIA TITAN X","NVIDIA RTX 3260 ti","NVIDIA RTX 3070","NVIDIA RTX 3080")

        for (i in procesadores) {
            reqProce += 1
            if (i == procesador)
                break
        }

        for (k in graficas) {
            reqGraf += 1
            if (k == grafica)
                break
        }

        return if (pc.procesador.code > reqProce && pc.grafica.code > reqGraf){
            "Es Apto"
        }else{
            "No es Apto"
        }
    }
}