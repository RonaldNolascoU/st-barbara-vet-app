package com.dm.vsb

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
class CitaAdapter(private val citas: List<Cita>) : RecyclerView.Adapter<CitaAdapter.ViewHolder>() {

    private var onItemClick: OnItemClickListener? = null

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val pacienteTextView: TextView = view.findViewById(R.id.tvPaciente)
        val motivoTextView: TextView = view.findViewById(R.id.tvMotivo)
        val fechaTextView: TextView = view.findViewById(R.id.tvFecha)
        val doctorTextView: TextView = view.findViewById(R.id.tvDoctor)
        val observacionTextView: TextView = view.findViewById(R.id.tvObservacion)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.cita_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cita = citas[position]
        holder.pacienteTextView.text = "Paciente: " + cita.pacientes
        holder.motivoTextView.text = "Motivo: " + cita.motivo
        holder.fechaTextView.text = "Fecha: " + cita.fecha
        holder.doctorTextView.text = "Doctor: " + cita.doctor
        holder.observacionTextView.text = "Observacion: " + cita.observaciones

        // Agrega el escuchador de clics a la vista del elemento de la lista
        holder.itemView.setOnClickListener {
            onItemClick?.onItemClick(cita)
        }
    }

    override fun getItemCount(): Int {
        return citas.size
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        onItemClick = listener
    }

    interface OnItemClickListener {
        fun onItemClick(cita: Cita)

    }
}