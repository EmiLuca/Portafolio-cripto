package com.example.tp_grupol

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
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
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

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
        cbRemember.setOnClickListener {
            pedirPermisoNotificaciones()
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
            notificacionsimple(usuario.nombre.toString())
        }

        inicio(usuario.nombre)
    }

    private fun notificacionsimple(usuario: String) {
        val canalId = "canal_simple"
        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val canal = NotificationChannel(
                canalId,
                "Canal simple",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            manager.createNotificationChannel(canal)
        }

        val notification = NotificationCompat.Builder(this, canalId)
            .setSmallIcon(R.drawable.baseline_work_outline_24)
            .setContentTitle("Hello! $usuario")
            .setContentText("We will remember your user")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .build()

        manager.notify(1, notification)
    }

    private fun inicio(nombre: String) {
        Toast.makeText(this, "Welcome $nombre", Toast.LENGTH_SHORT).show()
        val intent = Intent(this, Portfolio::class.java)
        startActivity(intent)
        finish()
    }
    private fun pedirPermisoNotificaciones() {
        // Solo necesario desde Android 13 en adelante
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            // Verificar si ya tiene el permiso
            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                // Mostrar diálogo de solicitud
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                    1001
                )
            }
        }
    }
}