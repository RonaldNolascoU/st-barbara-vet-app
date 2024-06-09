package com.dm.vsb

import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import java.text.SimpleDateFormat
import java.util.Locale

class NuevaCitaActivity : AppCompatActivity() {

    private val calendar= Calendar.getInstance()
    lateinit var txtFecha: TextView
    lateinit var btnFecha: Button
    lateinit var motivo: EditText
    lateinit var estado: EditText
    lateinit var observaciones: EditText

    private lateinit var paciente: String
    private lateinit var doctor: String

    private val db = FirebaseFirestore.getInstance()
    private val collecionPacientes = db.collection("pacientes")
    private val collecionDoctores = db.collection("doctores")

    private val citasCollection = db.collection("citas")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_nueva_cita)

        inicializandoSpinners()

        txtFecha = findViewById(R.id.textFecha)
        btnFecha =findViewById(R.id.buttonFecha)
        motivo = findViewById(R.id.editTextMotivo)
        estado = findViewById(R.id.editTextEstado)
        observaciones = findViewById(R.id.editTextEmail)


        val btnIngresar: Button = findViewById(R.id.buttonRegistrar)
        val btnCancelar: Button = findViewById(R.id.buttonCancelar)

        btnIngresar.setOnClickListener {

            if(paciente.equals("Seleccionar...") || doctor.equals("Seleccionar...")){
                Toast.makeText(this, "Debe seleccionar un paciente o doctor", Toast.LENGTH_SHORT).show()
            }else{
                val cita = hashMapOf(
                    "fecha" to txtFecha.text.toString(),
                    "paciente" to paciente,
                    "doctor" to doctor,
                    "motivo" to motivo.text.toString(),
                    "estado" to estado.text.toString(),
                    "observaciones" to observaciones.text.toString()
                )
                citasCollection.add(cita)
                    .addOnSuccessListener {
                        Toast.makeText(this, "Nueva cita agendada", Toast.LENGTH_SHORT).show()
                        finish()
                    }
                    .addOnFailureListener { e ->
                        Toast.makeText(this, "Error al agendar la cita: ${e.message}", Toast.LENGTH_SHORT).show()
                    }
//                Toast.makeText(this, "Nueva cita agendada", Toast.LENGTH_SHORT).show()
//                finish()
            }


        }

        btnCancelar.setOnClickListener {
            finish()
        }

        btnFecha.setOnClickListener {
            showDatePicker()
        }
    }

    fun inicializandoSpinners(){
        var spinnerPaciente: Spinner = findViewById(R.id.spinnerPaciente)
        var spinnerDoctor: Spinner = findViewById(R.id.spinnerDoctor)

        var pacientes = ArrayList<String>()
        var doctores = ArrayList<String>()

        pacientes.add("Seleccionar...")
        doctores.add("Seleccionar...")

        collecionPacientes.get().addOnSuccessListener {
            querySnapshot->
            for (registro in querySnapshot){
                pacientes.add(registro.getString("Nombre").toString())
            }
        }
        collecionDoctores.get().addOnSuccessListener {
            querySnapshot->
            for(registro in querySnapshot){
                doctores.add(registro.getString("Nombre").toString())
            }
        }



        var aaPacientes = ArrayAdapter(this,android.R.layout.simple_spinner_item,pacientes)

        var aaDoctores = ArrayAdapter(this,android.R.layout.simple_spinner_item,doctores)

        spinnerPaciente.adapter=aaPacientes
        spinnerDoctor.adapter=aaDoctores

        spinnerPaciente.onItemSelectedListener=object:
            AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>, view: View, position:Int, id:Long){
                //Codigo para almacenar los datos en variables al seleccionar
                    paciente=pacientes[position]
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
                doctor=doctores[position]
                //Toast.makeText(this@NuevaCitaActivity,"Has seleccionado al doctor: " + doctores[position], Toast.LENGTH_SHORT).show()
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