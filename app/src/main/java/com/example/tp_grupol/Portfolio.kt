package com.example.tp_grupol

import Coins
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView

class Portfolio : AppCompatActivity() {

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

        rvCoins = findViewById(R.id.rvCoins)

        coinAdapter = CoinAdapter(getCoins(), this)
        rvCoins.adapter = coinAdapter

    }

    private fun getCoins(): MutableList<Coins> {
        return mutableListOf(
            Coins(
                id = "bitcoin",
                nombre = "Bitcoin",
                simCot = "BTC",
                icono = "https://assets.coingecko.com/coins/images/1/large/bitcoin.png",
                precioActual = 68000.0,
                cambioPrecio24HS = -1.5
            ),
            Coins(
                id = "ethereum",
                nombre = "Ethereum",
                simCot = "ETH",
                icono = "https://assets.coingecko.com/coins/images/279/large/ethereum.png",
                precioActual = 3200.0,
                cambioPrecio24HS = +2.1
            ),
            Coins(
                id = "cardano",
                nombre = "Cardano",
                simCot = "ADA",
                icono = "https://assets.coingecko.com/coins/images/975/large/cardano.png",
                precioActual = 0.45,
                cambioPrecio24HS = +0.7
            )
        )
    }
}