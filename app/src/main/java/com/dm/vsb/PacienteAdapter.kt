package com.dm.vsb

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PacienteAdapter (private val pacientes: List<Paciente>) : RecyclerView.Adapter<PacienteAdapter.ViewHolder>() {

    private var onItemClick: OnItemClickListener? = null

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nombreTextView: TextView = view.findViewById(R.id.tvNombre)
        val edadTextView: TextView = view.findViewById(R.id.tvEdad)
        val especieTextView: TextView = view.findViewById(R.id.tvEspecie)
        val razaTextView: TextView = view.findViewById(R.id.tvRaza)
        val propietarioTextView: TextView = view.findViewById(R.id.tvPropietario)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.paciente_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val paciente = pacientes[position]
        holder.nombreTextView.text = "Nombre: "+paciente.nombre
        holder.edadTextView.text = "Edad: "+paciente.edad
        holder.especieTextView.text = "Especie: "+paciente.especie
        holder.razaTextView.text = "Raza: "+paciente.raza
        holder.propietarioTextView.text = "Propietario: "+paciente.propietario

        // Agrega el escuchador de clics a la vista del elemento de la lista
        holder.itemView.setOnClickListener {
            onItemClick?.onItemClick(paciente)
        }
    }

    override fun getItemCount(): Int {
        return pacientes.size
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        onItemClick = listener
    }

    interface OnItemClickListener {
        fun onItemClick(paciente: Paciente)

    }
}