package com.example.tp_grupol
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CoinsDao {
    @Query("SELECT * FROM coins")
    fun getAll(): List<Coins>
    @Insert
    fun insert(coins: Coins)
}