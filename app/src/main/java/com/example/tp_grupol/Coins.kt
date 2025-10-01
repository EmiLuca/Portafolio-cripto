package com.example.tp_grupol

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "coins")
data class Coins(
    @ColumnInfo(name = "Nombre") val nombre: String,
    @ColumnInfo(name = "simbolo de cotizacion") val simCot: String,
    @ColumnInfo(name = "icono") val icono: String,
    @ColumnInfo(name = "precio") val precioActual: Double
){@PrimaryKey(autoGenerate = true) var id: Int = 0}