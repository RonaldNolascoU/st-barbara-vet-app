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
 * Use the [Doctores.newInstance] factory method to
 * create an instance of this fragment.
 */
class Doctores : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private val db = FirebaseFirestore.getInstance()
    private val coleccion = db.collection("doctores")
    private lateinit var recyclerView: RecyclerView
    private lateinit var btnNuevoDoctor: FloatingActionButton
    private lateinit var adapter: DoctorAdapter
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
        btnNuevoDoctor = view.findViewById(R.id.fab_agregar)
        recyclerView.layoutManager=LinearLayoutManager(context)
        cargarDoctores()

        btnNuevoDoctor.setOnClickListener {
            val intent = Intent(context, NuevoDoctorActivity::class.java)
            startActivity(intent)
        }

        return view
    }

    fun cargarDoctores(){

        coleccion.get().addOnSuccessListener {
            querySnapshot ->
            val lista = mutableListOf<Doctor>()
            for(elemento in querySnapshot){
                val nombre = elemento.getString("Nombre").toString()
                val apellido = elemento.getString("Apellido").toString()
                val especialidad = elemento.getString("Especialidad").toString()
                val email = elemento.getString("Email").toString()
                val telefono = elemento.getString("Telefono").toString()

                val modelo = Doctor(nombre,apellido,especialidad,telefono,email)
                lista.add(modelo)
            }
            adapter=DoctorAdapter(lista)
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
         * @return A new instance of fragment Doctores.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Doctores().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}