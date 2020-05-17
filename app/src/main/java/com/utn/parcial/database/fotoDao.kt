package com.utn.parcial.database

import androidx.room.*
import com.utn.parcial.entities.Foto


@Dao
interface fotoDao {

    @Query("SELECT * FROM Fotos ORDER BY id")
    fun loadAllFotos(): MutableList<Foto?>?

    @Query("SELECT * FROM Fotos WHERE maceta_id = :maceta_id")
    fun loadAllFotosMaceta(maceta_id: Int): MutableList<Foto?>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFoto(foto: Foto?)

    @Update
    fun updateFoto(foto: Foto?)

    @Delete
    fun delete(foto: Foto?)

    @Query("SELECT COUNT(*) FROM Fotos")
    fun cantFotos(): Int?

    @Query("DELETE FROM Fotos WHERE id = :id")
    fun deleteFromId(id: Int?)
}