package com.utn.parcial.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey


@Entity(tableName = "plantas")
class Planta (especie: String, hTierra: String, hHambiente: String, tHambiente : String, foto: String ) {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id : Int? = null

    @ColumnInfo(name = "especie")
    var especie: String

    @ColumnInfo(name = "hTierra")
    var hTierra: String

    @ColumnInfo(name = "hHambiente")
    var hHambiente: String

    @ColumnInfo(name = "tHambiente")
    var tHambiente : String

    @ColumnInfo(name = "@drawable/defoultPlanta")
    var foto: String

    init {
        this.especie = especie
        this.hTierra = hTierra
        this.hHambiente = hHambiente
        this.tHambiente = tHambiente
        this.foto = foto
    }
}