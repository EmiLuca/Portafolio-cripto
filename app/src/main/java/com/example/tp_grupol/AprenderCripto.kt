package com.example.tp_grupol

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.transition.TransitionManager

class AprenderCripto: AppCompatActivity(), PrimerFragmentoInterfaz {

    lateinit var toolbar: Toolbar
    private var isExpanded = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.aprender_cripto) // ¡Corregido!

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar!!.title=resources.getString(R.string.toolbar)
        toolbar.overflowIcon = ContextCompat.getDrawable(this, R.drawable.outline_arrow_back_24)


        val primerFragmento = supportFragmentManager
            .findFragmentById(R.id.contenedor_primer_fragmento) as? PrimerFragmento

        primerFragmento?.listener = this
    }

    override fun mostrarContenido() {
        if (!isExpanded) {
            val mainLayout = findViewById<LinearLayout>(R.id.main)
            val segundoFragmentoContenedor = findViewById<FrameLayout>(R.id.contenedor_segundo_fragmento)

            // aviso de animación
            TransitionManager.beginDelayedTransition(mainLayout)
            segundoFragmentoContenedor.visibility = View.VISIBLE // este es el cambio visual

            val segundoFragmento = SegundoFragmento()
            supportFragmentManager.beginTransaction()
                .add(R.id.contenedor_segundo_fragmento, segundoFragmento)
                .commit()

            isExpanded = true
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_portafolio, menu)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.item_volver){
            val intent = Intent(this, Portfolio::class.java)
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }

}