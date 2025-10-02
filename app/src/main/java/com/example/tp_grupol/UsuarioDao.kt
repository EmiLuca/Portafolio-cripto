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

    @Query("SELECT * FROM Usuario WHERE Email = :email LIMIT 1")
    fun getUsuarioByEmail(email: String): Usuario?
    // LIMIT 1 asegura que solo devuelva un registro aunque haya duplicados

    @Query("SELECT * FROM Usuario WHERE Email = :email AND contraseña = :password LIMIT 1")
    fun login(email: String, password: String): Usuario?
}
