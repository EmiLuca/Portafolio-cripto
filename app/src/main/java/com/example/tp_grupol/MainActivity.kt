package com.example.tp_grupol

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    lateinit var etEmail: EditText
    lateinit var etPassword: EditText
    lateinit var cbRemember: CheckBox
    lateinit var btnCreateAccount: Button
    lateinit var btnLogin: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        etEmail = findViewById(R.id.Nombre) // tu EditText de email
        etPassword = findViewById(R.id.Contrasena)
        cbRemember = findViewById(R.id.BoxUsuario)
        btnCreateAccount = findViewById(R.id.Guardarusuario)
        btnLogin = findViewById(R.id.IniciarSesion)

        // Cargar usuario guardado
        val prefs = getSharedPreferences(getString(R.string.preferencias), MODE_PRIVATE)
        val savedEmail = prefs.getString(getString(R.string.usuario), "")
        val savedPassword = prefs.getString(getString(R.string.contraseña), "")

        if (!savedEmail.isNullOrEmpty() && !savedPassword.isNullOrEmpty()) {
            login(savedEmail, savedPassword, remember = false)
        }

        btnCreateAccount.setOnClickListener {
            startActivity(Intent(this, CrearCuenta::class.java))
        }

        btnLogin.setOnClickListener {
            val email = etEmail.text.toString().trim()
            val password = etPassword.text.toString()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Complete all fields to continue.", Toast.LENGTH_SHORT).show()
            } else {
                login(email, password, cbRemember.isChecked)
            }
        }
    }

    private fun login(email: String, password: String, remember: Boolean) {
        // Verificar usuario en Room
        val usuario = AppDatabase.getDatabase(applicationContext).usuariodao().login(email, password)

        if (usuario == null) {
            Toast.makeText(this, "Invalid email or password.", Toast.LENGTH_SHORT).show()
            return
        }

        // Guardar usuario si "recordar" está marcado
        if (remember) {
            val prefs = getSharedPreferences(getString(R.string.preferencias), MODE_PRIVATE)
            prefs.edit().putString(getString(R.string.usuario), email).putString(getString(R.string.contraseña), password).apply()
            notificacion()
        }

        inicio(usuario.nombre)
    }

    private fun notificacion() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS)
                != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.POST_NOTIFICATIONS), 1001)
            }
        }
        val intent = Intent(this, notificacion::class.java) // sin paréntesis
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            startForegroundService(intent)
        } else {
            startService(intent)
        }
    }

    private fun inicio(nombre: String) {
        Toast.makeText(this, "Welcome $nombre", Toast.LENGTH_SHORT).show()
        val intent = Intent(this, Portfolio::class.java)
        startActivity(intent)
        finish()
    }
}
