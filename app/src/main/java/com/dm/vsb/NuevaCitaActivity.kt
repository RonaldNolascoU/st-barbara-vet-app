package com.dm.vsb

import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.text.SimpleDateFormat
import java.util.Locale

class NuevaCitaActivity : AppCompatActivity() {

    private val calendar= Calendar.getInstance()
    lateinit var txtFecha: TextView
    lateinit var btnFecha: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_nueva_cita)

        inicializandoSpinners()

        txtFecha = findViewById(R.id.textFecha)
        btnFecha =findViewById(R.id.buttonFecha)

        val btnIngresar: Button = findViewById(R.id.buttonRegistrar)

        btnIngresar.setOnClickListener {
            Toast.makeText(this, "Nueva cita agendada", Toast.LENGTH_SHORT).show()
            finish()
        }

        btnFecha.setOnClickListener {
            showDatePicker()
        }
    }

    fun inicializandoSpinners(){
        var spinnerPaciente: Spinner = findViewById(R.id.spinnerPaciente)
        var spinnerDoctor: Spinner = findViewById(R.id.spinnerDoctor)

        var pacientes = arrayOf("Pablo","Pepe","Patricio","Paola","Pamela")
        var doctores = arrayOf("House","Tree","Grey","Donald","Diego")

        var aaPacientes = ArrayAdapter(this,android.R.layout.simple_spinner_item,pacientes)

        var aaDoctores = ArrayAdapter(this,android.R.layout.simple_spinner_item,doctores)

        spinnerPaciente.adapter=aaPacientes
        spinnerDoctor.adapter=aaDoctores

        spinnerPaciente.onItemSelectedListener=object:
            AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>, view: View, position:Int, id:Long){
                //Codigo para almacenar los datos en variables al seleccionar

                //Mensaje que indica seleccion realizada
                //Toast.makeText(this@NuevaCitaActivity,"Has seleccionado al paciente: " + pacientes[position], Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                //No hacer nada :D
            }

        }

        spinnerDoctor.onItemSelectedListener=object:
            AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>, view: View, position:Int, id:Long){
                Toast.makeText(this@NuevaCitaActivity,"Has seleccionado al doctor: " + doctores[position], Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                //No hacer nada :D
            }

        }
    }

    fun showDatePicker(){
        val datePickerDialog= DatePickerDialog(this,{DatePicker, year:Int, monthOfyear:Int, dayOfMonth:Int->
            val selectedDate : Calendar= Calendar.getInstance()
            selectedDate.set(year,monthOfyear,dayOfMonth)
            val dateFormat= SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            val formattedDate: String = dateFormat.format(selectedDate.time)
            txtFecha.text=formattedDate
        },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        datePickerDialog.show()
    }
}