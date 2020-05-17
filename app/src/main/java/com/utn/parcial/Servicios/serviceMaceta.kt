package com.utn.parcial.Servicios


import android.util.Log
import android.view.View
import com.utn.parcial.entities.Planta
import com.utn.parcial.database.appDatabase
import com.utn.parcial.database.macetaDao
import com.utn.parcial.database.plantaDao
import com.utn.parcial.entities.Maceta


class serviceMaceta(v:View) {

    private var maceta : Maceta? = null
    private var error : String =""
    private var db: appDatabase? = null
    private var macetaDao: macetaDao? = null
    private var plantaDao: plantaDao? = null
    private var macetasList: MutableList<Maceta?>? = null


    init {
        db = appDatabase.getAppDataBase(v.context)
        macetaDao = db?.macetaDao()
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

    fun getAllMacetas() : MutableList<Maceta?>?
    {
        Log.d("ser","1")
        if(macetaDao?.cantMacetas()==0)
        {
            Log.d("ser","2")
            this.newMaceta(Maceta("Primer maceta","@drawable/defoult_planta",0, ""))
        }
        Log.d("ser","3")
        return macetaDao?.loadAllMacetas()

    }

    fun newMaceta(maceta: Maceta?)
    {

        macetaDao?.insertMaceta(maceta)
    }

    fun getAllEspecies():MutableList<String>?
    {
        return plantaDao?.loadAllEspecie()
    }

}