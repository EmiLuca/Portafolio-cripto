package com.example.tp_grupol

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Coins(
    var id: String,
    var symbol: String,
    var name: String,
    var image: String,
    var current_price: Double,
    var market_cap_rank: Int,
    var market_cap: Long
)