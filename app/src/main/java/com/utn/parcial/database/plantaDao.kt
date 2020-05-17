package com.utn.parcial.database

import androidx.room.*
import com.utn.parcial.entities.Planta


@Dao
public interface plantaDao {

    @Query("SELECT * FROM plantas ORDER BY id")
    fun loadAllPlanta(): MutableList<Planta?>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPlanta(planta: Planta?)

    @Update
    fun updatePlanta(planta: Planta?)


    //@Delete
    //  fun delete(user: User?)

    @Query("SELECT * FROM plantas WHERE id = :id")
    fun loadPlantaById(id: Int): Planta?

    @Query("SELECT * FROM plantas WHERE especie = :especie")
    fun loadPlantaByUser(especie: String): Planta?

    @Query("SELECT especie FROM plantas")
    fun loadAllEspecie(): MutableList<String>

    @Query("SELECT COUNT(*) FROM plantas")
    fun cantPlantas(): Int?

}