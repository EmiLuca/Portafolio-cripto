package com.example.tp_grupol

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load

class CoinAdapter (var coins: MutableList<Coins>, var context: Context) : RecyclerView.Adapter<CoinAdapter.CoinViewHolder>() {
    class CoinViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var tvSymbol: TextView
        var tvName: TextView
        var ivImage: ImageView
        var tvCurrentPrice: TextView
        var tvMarketCapRank: TextView

        init {
            tvSymbol = view.findViewById(R.id.tvSymbol)
            tvName = view.findViewById(R.id.tvName)
            ivImage = view.findViewById(R.id.ivImage)
            tvCurrentPrice = view.findViewById(R.id.tvCurrentPrice)
            tvMarketCapRank = view.findViewById(R.id.tvMarketCapRank)
        }
    }
    override fun getItemCount() = coins.size

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): CoinViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_coins, viewGroup, false)
        return CoinViewHolder(view)
    }

    override fun onBindViewHolder(holder: CoinViewHolder, position: Int) {
        val item = coins.get(position)
        holder.tvSymbol.text = item.symbol.uppercase()
        holder.tvName.text = item.name
        holder.tvCurrentPrice.text = "$${item.current_price}"
        holder.tvMarketCapRank.text = "#${item.market_cap_rank}"

        // Cargar imagen con Coil
        holder.ivImage.load(item.image) {
            crossfade(true)
        }
    }
}