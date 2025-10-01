package com.example.tp_grupol

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
@Dao
interface UsuarioDao {
        @Query("SELECT * FROM Usuario")
        fun getAll(): List<Usuario>
        @Insert
        fun insert(usuario: Usuario)
    }
