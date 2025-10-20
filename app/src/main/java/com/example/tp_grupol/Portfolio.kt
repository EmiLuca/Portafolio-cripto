package com.example.tp_grupol

import com.example.tp_grupol.Coins
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class Portfolio : AppCompatActivity() {

    lateinit var toolbar: Toolbar
    lateinit var rvCoins: RecyclerView
    lateinit var coinAdapter: CoinAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_portfolio)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        toolbar= findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar!!.title=resources.getString(R.string.toolbar)
        toolbar.overflowIcon = ContextCompat.getDrawable(this, R.drawable.baseline_work_outline_24)

        rvCoins = findViewById(R.id.rvCoins)
        rvCoins.layoutManager = LinearLayoutManager(this)
        coinAdapter = CoinAdapter(mutableListOf(), this)
        rvCoins.adapter = coinAdapter


        // Implementacion de API
        val api = RetrofitClient.retrofit.create(ApiEndpoints::class.java)

        lifecycleScope.launch {
            while (isActive) {
                try {
                    val coins = withContext(Dispatchers.IO) {
                        api.getCoins(vsCurrency = "usd", perPage = 250)
                    }
                    coinAdapter.coins.clear()
                    coinAdapter.coins.addAll(coins)
                    coinAdapter.notifyDataSetChanged()

                } catch (e: Exception) {
                    Log.e("API", "Error: ${e.message}")
                }
                delay(60_000)
            }
        }
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_configuracion, menu)
        menuInflater.inflate(R.menu.menu_cerrar_sesion, menu)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menu_configuracion){
            val intent = Intent(this, Configuration::class.java)
        startActivity(intent)
        }
        if(item.itemId == R.id.menu_cerrar_sesion){
            val intent = Intent(this, MainActivity::class.java)
            var preferencias = getSharedPreferences(resources.getString(R.string.preferencias), MODE_PRIVATE)
            preferencias.edit().putString(resources.getString(R.string.usuario), "").apply()
            preferencias.edit().putString(resources.getString(R.string.contraseña), "").apply()
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }
}
