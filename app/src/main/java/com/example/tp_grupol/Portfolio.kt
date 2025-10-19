package com.example.tp_grupol

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView

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
        coinAdapter = CoinAdapter(getCoins(), this)
        rvCoins.adapter = coinAdapter
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

    private fun getCoins(): MutableList<Coins> {
        return mutableListOf(
            Coins(
                id ="bitcoin",
                symbol = "btc",
                name = "Bitcoin",
                image = "https://coin-images.coingecko.com/coins/images/1/large/bitcoin.png?1696501400",
                currentPrice = 108575,
                marketCapRank = 1,
                marketCap = 2163033633862
            )
        )
    }
}
