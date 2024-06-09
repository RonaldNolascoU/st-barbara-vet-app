package com.dm.vsb

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.firestore.FirebaseFirestore

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Citas.newInstance] factory method to
 * create an instance of this fragment.
 */


class Citas : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private val db = FirebaseFirestore.getInstance()
    private val coleccion = db.collection("citas")
    private lateinit var recyclerView: RecyclerView
    private lateinit var btnNuevaCita: FloatingActionButton
    private lateinit var adapter: CitaAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_doctores, container, false)
        recyclerView = view.findViewById(R.id.recyclerView)
        btnNuevaCita = view.findViewById(R.id.fab_agregar)
        recyclerView.layoutManager= LinearLayoutManager(context)
        cargarCitas()

        btnNuevaCita.setOnClickListener {
            val intent = Intent(context, NuevaCitaActivity::class.java)
            startActivity(intent)
        }

        return view

//        return inflater.inflate(R.layout.fragment_citas, container, false)
    }

    fun cargarCitas(){

        coleccion.get().addOnSuccessListener {
                querySnapshot ->
            val lista = mutableListOf<Cita>()
            for(elemento in querySnapshot){
                val fecha = elemento.getString("fecha").toString()
                val motivo = elemento.getString("motivo").toString()
                val estado = elemento.getString("estado").toString()
                val paciente = elemento.getString("paciente").toString()
                val doctor = elemento.getString("doctor").toString()
                val observaciones = elemento.getString("observaciones").toString()

                val modelo = Cita(fecha, motivo, estado, paciente, doctor, observaciones)
                lista.add(modelo)
            }
            adapter=CitaAdapter(lista)
            recyclerView.adapter=adapter
        }

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Citas.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Citas().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}