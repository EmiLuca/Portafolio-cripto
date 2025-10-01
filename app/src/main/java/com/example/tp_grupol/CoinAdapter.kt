package com.example.tp_grupol

import com.example.tp_grupol.Coins
import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CoinAdapter (var coins: MutableList<Coins>, var context: Context) : RecyclerView.Adapter<CoinAdapter.CoinViewHolder>() {
    class CoinViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ivLogo: ImageView
        val tvNombre: TextView
        val tvSimCot: TextView
        val tvPrecioActual: TextView
        val tvTotal: TextView
        val tvCantidad: TextView

        init {
            ivLogo = view.findViewById(R.id.ivLogo)
            tvNombre = view.findViewById(R.id.tvNombre)
            tvSimCot = view.findViewById(R.id.tvSimCot)
            tvPrecioActual = view.findViewById(R.id.tvPrecioActual)
            tvTotal = view.findViewById(R.id.tvTotal)
            tvCantidad = view.findViewById(R.id.tvCantidad)
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
        holder.tvNombre.text = item.nombre
        holder.tvSimCot.text = item.simCot
        holder.tvPrecioActual.text = "$${item.precioActual}"
        holder.tvTotal.text = "$${item.precioActual}"
    }

    // Para refrescar la lista cuando llegan nuevos datos
    fun updateData(newList: List<Coins>) {
        coins.clear()
        coins.addAll(newList)
        notifyDataSetChanged()
    }
}