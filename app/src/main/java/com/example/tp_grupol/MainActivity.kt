package com.example.tp_grupol

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    lateinit var Nombre: EditText
    lateinit var Contrasena: EditText
    lateinit var CBOX: CheckBox
    lateinit var CrearUsuario: Button
    lateinit var Inicio: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        Nombre= findViewById(R.id.Nombre)
        Contrasena= findViewById(R.id.Contrasena)
        CBOX= findViewById(R.id.BoxUsuario)
        CrearUsuario= findViewById(R.id.Guardarusuario)
        Inicio= findViewById(R.id.IniciarSesion)

        CrearUsuario.setOnClickListener {
            Toast.makeText(this, "Go To Create User layout.", Toast.LENGTH_SHORT).show()
        }
        Inicio.setOnClickListener {
            if (Nombre.text.toString().isEmpty() || Contrasena.text.toString().isEmpty()) {
                Toast.makeText(
                    this,
                    "Complete all fields to continue.",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                val pagina2 = Intent(this, Portfolio::class.java)
                startActivity(pagina2)
            }
        }
    }

}