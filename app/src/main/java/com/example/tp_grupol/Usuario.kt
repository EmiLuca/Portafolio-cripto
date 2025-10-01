package com.example.tp_grupol

import android.R
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Usuario")
data class Usuario(
    @ColumnInfo(name = "Nombre") val nombre: String,
    @ColumnInfo(name = "contraseña") val contrasena: String,
    @ColumnInfo(name = "Email") val Email: String
){
    @PrimaryKey(autoGenerate = true) var id:Int = 0
}