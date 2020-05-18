package com.utn.parcial.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Fotos")
class Foto (maceta_id: Int, foto: String){

        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "id")
        var id: Int? = null

        //@ForeignKey(entity = Maceta::class,parentColumns = ["id"], childColumns = ["maceta_id"])
        @ColumnInfo(name = "maceta_id")
        var maceta_id : Int

        @ColumnInfo(name = "foto")
        var foto : String

        // @ColumnInfo(name = "delete")
        // var delete : String

        init {

            this.maceta_id = maceta_id
            this.foto = foto
            //This.delete = false        Tendria que implementar el metodo de borrado
        }

    }