package com.utn.parcial.database
import androidx.room.*
import com.utn.parcial.entities.Maceta


@Dao
public interface macetaDao {

    @Query("SELECT * FROM macetas ORDER BY id")
    fun loadAllMacetas(): MutableList<Maceta?>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMaceta(maceta: Maceta?)

    @Update
    fun updateMaceta(maceta: Maceta?)

    @Query("SELECT COUNT(*) FROM macetas")
    fun cantMacetas(): Int?

}