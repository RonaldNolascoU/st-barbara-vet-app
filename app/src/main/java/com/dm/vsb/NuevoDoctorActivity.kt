package com.dm.vsb

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class NuevoDoctorActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_nuevo_doctor)

        val btnIngresar: Button = findViewById(R.id.buttonRegistrar)

        btnIngresar.setOnClickListener {
            Toast.makeText(this, "Nuevo doctor registrado", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}