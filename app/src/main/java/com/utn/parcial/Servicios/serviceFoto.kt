package com.utn.parcial.Servicios

import android.util.Log
import android.view.View
import com.utn.parcial.database.appDatabase
import com.utn.parcial.database.fotoDao
import com.utn.parcial.entities.Foto

class serviceFoto(v: View) {


    private var error : String =""
    private var db: appDatabase? = null
    private var fotoDao: fotoDao? = null



    init {
        db = appDatabase.getAppDataBase(v.context)
        fotoDao = db?.fotoDao()
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

    fun getAllFotos(maceta_id: Int) : MutableList<Foto?>?
    {

        if(fotoDao?.cantFotos()==0)
        {
           //Guardar foto nueva
        }
        return fotoDao?.loadAllFotosMaceta(maceta_id)

    }

    fun newFoto(foto: Foto?)
    {

        fotoDao?.insertFoto(foto)
    }

    fun getFotoId(id : Int) : String
    {
        return fotoDao?.loadFoto(id)!!
    }

    fun getUltimaFoto(maceta_id: Int) : String
    {
        if ( fotoDao?.cantFotosMaceta(maceta_id)!=0)
        {
            return fotoDao?.loadUltimaFoto(maceta_id)!!
        }
        return  "@drawable/defoult_planta"
    }

    fun deletFoto (id: Int?){
        fotoDao?.deleteFromId(id)
    }

}