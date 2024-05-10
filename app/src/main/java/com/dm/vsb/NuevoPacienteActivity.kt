package com.dm.vsb

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class NuevoPacienteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_nuevo_paciente)
        inicializandoSpinners()

        val btnIngresar: Button = findViewById(R.id.buttonRegistrar)

        btnIngresar.setOnClickListener {
            Toast.makeText(this, "Nuevo paciente registrado", Toast.LENGTH_SHORT).show()
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

                //Mensaje que indica seleccion realizada
                //Toast.makeText(this@NuevoPacienteActivity,"Has seleccionado la especie: " + especies[position], Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                //No hacer nada :D
            }

        }
    }
}