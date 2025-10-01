package com.example.tp_grupol

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
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
            if(editPassword.text.toString().isEmpty()||editEmail.text.toString().isEmpty()||editName.text.toString().isEmpty()||editConfirmPassword.text.toString().isEmpty()){
                Toast.makeText(this, "You must complete all fields.", Toast.LENGTH_SHORT).show()
            }else{
                guardarusuario(editName.text.toString(), editPassword.text.toString(), editEmail.text.toString())
            }
        }

    }
    private fun guardarusuario(nombre: String, constrasena: String, email: String){
        var nuevousuario= Usuario(nombre, constrasena, email)
        AppDatabase.getDatabase(applicationContext).usuariodao().insert(nuevousuario)
        val portfolio = Intent(this,Portfolio::class.java)
        startActivity(portfolio)
    }

}