package com.dm.vsb

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore

class NuevoDoctorActivity : AppCompatActivity() {

    private val db = FirebaseFirestore.getInstance()
    private val collecion = db.collection("doctores")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_nuevo_doctor)


        val txtNombre: EditText = findViewById(R.id.editTextNombre)
        val txtApellido: EditText = findViewById(R.id.editTextApellido)
        val txtEspecialidad: EditText = findViewById(R.id.editTextEspecialidad)
        val txtTelefono: EditText = findViewById(R.id.editTextTelefono)
        val txtEmail: EditText = findViewById(R.id.editTextEmail)

        val btnIngresar: Button = findViewById(R.id.buttonRegistrar)
        val btnCancelar: Button = findViewById(R.id.buttonCancelar)

        btnIngresar.setOnClickListener {

            var nombre = txtNombre.text.toString()
            var apellido = txtApellido.text.toString()
            var especialidad = txtEspecialidad.text.toString()
            var telefono = txtTelefono.text.toString()
            var email = txtEmail.text.toString()

            val data = hashMapOf(
                "Nombre" to nombre,
                "Apellido" to apellido,
                "Especialidad" to especialidad,
                "Telefono" to telefono,
                "Email" to email
            )

            collecion.add(data).addOnSuccessListener {
                Toast.makeText(this, "Nuevo doctor registrado", Toast.LENGTH_SHORT).show()
                finish()
            }.addOnFailureListener{
                Toast.makeText(this, "Ha ocurrido un error", Toast.LENGTH_SHORT).show()
            }

        }

        btnCancelar.setOnClickListener {
            finish()
        }
    }
}