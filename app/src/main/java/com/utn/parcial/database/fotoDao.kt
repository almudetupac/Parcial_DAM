package com.utn.parcial.database

import androidx.room.*
import com.utn.parcial.entities.Foto


@Dao
interface fotoDao {

    @Query("SELECT * FROM Fotos ORDER BY id")
    fun loadAllFotos(): MutableList<Foto?>?

    @Query("SELECT * FROM Fotos WHERE maceta_id = :maceta_id")
    fun loadAllFotosMaceta(maceta_id: Int): MutableList<Foto?>?

    @Query("SELECT foto FROM Fotos WHERE id = :id")
    fun loadFoto(id: Int?): String?

    @Query("Select foto from fotos WHERE maceta_id = :maceta_id order by id desc limit 1 ")
    fun loadUltimaFoto(maceta_id: Int): String?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFoto(foto: Foto?)

    @Update
    fun updateFoto(foto: Foto?)

    @Delete
    fun delete(foto: Foto?)

    @Query("SELECT COUNT(*) FROM Fotos")
    fun cantFotos(): Int?

    @Query("SELECT COUNT(*) FROM Fotos WHERE maceta_id = :maceta_id")
    fun cantFotosMaceta(maceta_id: Int): Int?

    @Query("DELETE FROM Fotos WHERE id = :id")
    fun deleteFromId(id: Int?)
}