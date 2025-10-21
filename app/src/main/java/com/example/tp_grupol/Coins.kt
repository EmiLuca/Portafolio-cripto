package com.example.tp_grupol

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
@Entity(tableName = "coins")
data class Coins(
    @PrimaryKey val id: String,   // CoinGecko ya te da un id único
    val symbol: String,
    val name: String,
    val image: String,
    val current_price: Double,
    val market_cap_rank: Int,
    val market_cap: Long
)