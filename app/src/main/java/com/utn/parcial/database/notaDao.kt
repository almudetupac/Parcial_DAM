package com.utn.parcial.database

import androidx.room.*
import com.utn.parcial.entities.Nota

@Dao
interface notaDao {
    @Query("SELECT * FROM Notas ORDER BY id")
    fun loadAllNota(): MutableList<Nota?>?

    @Query("SELECT * FROM Notas WHERE maceta_id = :maceta_id")
    fun loadAllNotasMaceta(maceta_id: Int): MutableList<Nota?>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNota(nota: Nota?)

    @Update
    fun updateNota(nota: Nota?)

    @Delete
    fun delete(nota: Nota?)

    @Query("SELECT COUNT(*) FROM Notas WHERE maceta_id = :maceta_id")
    fun cantNotas(maceta_id:Int): Int?

    @Query("DELETE FROM Notas WHERE id = :id")
    fun deleteFromId(id: Int?)

}