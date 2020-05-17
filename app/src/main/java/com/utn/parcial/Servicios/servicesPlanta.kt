package com.utn.parcial.Servicios

import android.view.View
import com.utn.parcial.entities.Planta
import com.utn.parcial.database.appDatabase
import com.utn.parcial.database.plantaDao

class servicesPlanta (v : View) {

    private var planta : Planta? = null
    private var error : String =""
    private var db: appDatabase? = null
    private var plantaDao: plantaDao? = null

    init {
        db = appDatabase.getAppDataBase(v.context)
        plantaDao = db?.plantaDao()
    }

    private fun setError (Error : String)
    {
        this.error  = Error
    }

    fun getError () : String
    {
        var aux = this.error

        this.error = ""
        return aux
    }
    fun hayPlantas() : Int?
    {

        return this.plantaDao?.cantPlantas()
    }

     fun cargarListaPlantas()
     {
         this.plantaDao?.insertPlanta(Planta("My_Planta","25%","75%","25","@drawable/defoult_planta"))
         this.plantaDao?.insertPlanta(Planta("Tomate","25%","75%","25","@drawable/defoult_planta"))
         this.plantaDao?.insertPlanta(Planta("Morron","25%","75%","25","@drawable/defoult_planta"))
         this.plantaDao?.insertPlanta(Planta("Oregano","25%","75%","25","@drawable/defoult_planta"))
         this.plantaDao?.insertPlanta(Planta("Albahaca","25%","75%","25","@drawable/defoult_planta"))
         this.plantaDao?.insertPlanta(Planta("Perejil","25%","75%","25","@drawable/defoult_planta"))
         this.plantaDao?.insertPlanta(Planta("Aji","25%","75%","25","@drawable/defoult_planta"))


     }

    fun newPlanta(){
this.plantaDao?.insertPlanta(Planta("Especie","0%","0%","0°","@drawable/defoult_planta"))

    }

    fun getAllEspecies():MutableList<String>?
    {
        return plantaDao?.loadAllEspecie()
    }



}