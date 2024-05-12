package com.dm.vsb

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore

class NuevoPacienteActivity : AppCompatActivity() {

    private lateinit var especie : String

    private val db = FirebaseFirestore.getInstance()
    private val collecion = db.collection("pacientes")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_nuevo_paciente)
        inicializandoSpinners()

        val txtNombre: EditText = findViewById(R.id.editTextNombre)
        val txtRaza: EditText = findViewById(R.id.editTextRaza)
        val txtEdad: EditText = findViewById(R.id.editTextEdad)
        val txtPropietario: EditText = findViewById(R.id.editTextPropietario)



        val btnIngresar: Button = findViewById(R.id.buttonRegistrar)
        val btnCancelar: Button = findViewById(R.id.buttonCancelar)

        btnIngresar.setOnClickListener {

            var nombre = txtNombre.text.toString()
            var raza = txtRaza.text.toString()
            var edad = txtEdad.text.toString()
            var propietario = txtPropietario.text.toString()

            val data = hashMapOf(
                "Nombre" to nombre,
                "Raza" to raza,
                "Edad" to edad,
                "Propietario" to propietario,
                "Especie" to especie
            )

            collecion.add(data).addOnSuccessListener {
                Toast.makeText(this, "Nuevo paciente registrado", Toast.LENGTH_SHORT).show()
                finish()
            }.addOnFailureListener{
                Toast.makeText(this, "Ha ocurrido un error", Toast.LENGTH_SHORT).show()
            }
        }
        btnCancelar.setOnClickListener {
            finish()
        }
    }

    fun inicializandoSpinners(){
        var spinnerEspecie: Spinner = findViewById(R.id.spinnerEspecie)

        var especies = arrayOf("Perro","Gato","Tortuga","Canario","Serpiente")

        var aaEspecies = ArrayAdapter(this,android.R.layout.simple_spinner_item,especies)

        spinnerEspecie.adapter=aaEspecies


        spinnerEspecie.onItemSelectedListener=object:
            AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>, view: View, position:Int, id:Long){
                //Codigo para almacenar los datos en variables al seleccionar
                especie = especies[position].toString()
                //Mensaje que indica seleccion realizada
                //Toast.makeText(this@NuevoPacienteActivity,"Has seleccionado la especie: " + especies[position], Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                //No hacer nada :D
            }

        }
    }
}