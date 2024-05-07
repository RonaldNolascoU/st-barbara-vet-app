package com.dm.vsb

import android.app.ActivityManager
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.dm.vsb.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(Inicio())


        binding.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.inicio -> replaceFragment(Inicio())
                R.id.Doctores -> replaceFragment(Doctores())
                R.id.Pacientes -> replaceFragment(Pacientes())
                R.id.Citas -> replaceFragment(Citas())
                R.id.Perfil -> replaceFragment(Perfil())
                else -> {

                }


            }
            true
        }


    }
    private fun replaceFragment(fragment: Fragment){
        val fragmentManage = supportFragmentManager
        val fragmentTransaction = fragmentManage.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, fragment)
        fragmentTransaction.commit()
    }

}