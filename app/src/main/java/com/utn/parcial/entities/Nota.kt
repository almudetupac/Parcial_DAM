package com.utn.parcial.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey


@Entity(tableName = "Notas")
class Nota (maceta_id : Int, fecha : String, nota: String) {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int? = null

    //@ForeignKey(entity = Maceta::class,parentColumns = ["id"], childColumns = ["maceta_id"])
    @ColumnInfo(name = "maceta_id")
    var maceta_id : Int

    @ColumnInfo(name = "fecha")
    var fecha : String

    @ColumnInfo(name = "nota")
    var nota : String

   // @ColumnInfo(name = "delete")
   // var delete : String

    init {

        this.maceta_id = maceta_id
        this.fecha = fecha
        this.nota = nota
        //This.delete = false        Tendria que implementar el metodo de borrado
    }
}