package com.example.tp_grupol

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Coins(
    var id: String,
    var symbol: String,
    var name: String,
    var image: String,
    var currentPrice: Int,
    var marketCapRank: Int,
    var marketCap: Long
)