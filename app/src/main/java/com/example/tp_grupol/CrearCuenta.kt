package com.example.tp_grupol

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class CrearCuenta : AppCompatActivity() {

    lateinit var editName: EditText
    lateinit var editEmail: EditText
    lateinit var editPassword: EditText
    lateinit var editConfirmPassword: EditText
    lateinit var btnCreateAccount: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_crear_cuenta)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        editName = findViewById(R.id.editName)
        editEmail = findViewById(R.id.editEmail)
        editPassword = findViewById(R.id.editPassword)
        editConfirmPassword = findViewById(R.id.editConfirmPassword)
        btnCreateAccount = findViewById(R.id.btnCreateAccount)

        btnCreateAccount.setOnClickListener {
            //Trim para eliminar los espacios en blanco a los lados del texto
            val nombre = editName.text.toString().trim()
            val email = editEmail.text.toString().trim()
            val password = editPassword.text.toString()
            val confirmPassword = editConfirmPassword.text.toString()

            // Validación de campos vacíos
            if (nombre.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(this, "You must complete all fields.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Validación de contraseñas
            if (password != confirmPassword) {
                Toast.makeText(this, "Passwords do not match.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Verificar si el usuario ya existe
            val existingUser = AppDatabase.getDatabase(applicationContext).usuariodao().getUsuarioByEmail(email)
            if (existingUser != null) {
                Toast.makeText(this, "User already exists.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            guardarUsuario(nombre, password, email)
        }
    }

    private fun guardarUsuario(nombre: String, constrasena: String, email: String) {
        val nuevoUsuario = Usuario(nombre, constrasena, email)
        AppDatabase.getDatabase(applicationContext).usuariodao().insert(nuevoUsuario)

        Toast.makeText(this, "User created", Toast.LENGTH_SHORT).show()

        val portfolio = Intent(this, Portfolio::class.java)
        startActivity(portfolio)
        finish()
    }
}
