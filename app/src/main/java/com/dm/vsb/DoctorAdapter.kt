package com.dm.vsb

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DoctorAdapter(private val doctores: List<Doctor>) : RecyclerView.Adapter<DoctorAdapter.ViewHolder>() {

    private var onItemClick: OnItemClickListener? = null

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nombreTextView: TextView = view.findViewById(R.id.tvNombre)
        val especialidadTextView: TextView = view.findViewById(R.id.tvEspecialidad)
        val emailTextView: TextView = view.findViewById(R.id.tvEmail)
        val telefonoTextView: TextView = view.findViewById(R.id.tvTelefono)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.doctor_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val doctor = doctores[position]
        holder.nombreTextView.text = doctor.nombre + " " + doctor.apellido
        holder.especialidadTextView.text = doctor.especialidad
        holder.emailTextView.text = doctor.email
        holder.telefonoTextView.text = doctor.telefono

        // Agrega el escuchador de clics a la vista del elemento de la lista
        holder.itemView.setOnClickListener {
            onItemClick?.onItemClick(doctor)
        }
    }

    override fun getItemCount(): Int {
        return doctores.size
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        onItemClick = listener
    }

    interface OnItemClickListener {
        fun onItemClick(doctor: Doctor)

    }
}