package com.utn.parcial.entities



import android.os.Parcel
import android.os.Parcelable
import androidx.room.*
import com.utn.parcial.Servicios.servicesPlanta


@Entity(tableName = "macetas")
class Maceta (name: String, foto: String, planta_id : Int, especie : String){

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo (name = "id")
    var id : Int? = null

    @ColumnInfo (name = "name")
    var name : String?

    @ColumnInfo(name = "foto")
    var foto : String?


    @ForeignKey(entity = Planta::class,parentColumns = ["id"], childColumns = ["planta_id"])
    @ColumnInfo (name = "planta_id")
    var planta_id : Int


    @ForeignKey(entity = Planta::class,parentColumns = ["especie"], childColumns = ["especie"])
    @ColumnInfo (name = "especie")
    var especie : String?

    @Ignore
    lateinit var sPlanta: servicesPlanta



    init {

        this.name = name
        this.foto = foto
        this.planta_id = planta_id
        this.especie = especie
    }

}