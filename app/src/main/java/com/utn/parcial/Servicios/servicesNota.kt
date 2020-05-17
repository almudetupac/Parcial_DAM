package com.utn.parcial.Servicios

import android.content.ClipData
import android.util.Log
import android.view.View
import androidx.appcompat.view.menu.MenuView
import com.utn.parcial.database.appDatabase
import com.utn.parcial.database.notaDao
import com.utn.parcial.entities.Nota


class servicesNota(v:View) {
    private var nota : Nota? = null
    private var error : String =""
    private var db: appDatabase? = null
    private var notaDao: notaDao? = null
    private var notaList: MutableList<Nota?>? = null


    init {
        db = appDatabase.getAppDataBase(v.context)
        notaDao = db?.notaDao()

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
    fun getAllNotas(maceta_id: Int) : MutableList<Nota?>?
    {

        if(notaDao?.cantNotas(maceta_id)==0)
        {

            this.newNota(Nota(maceta_id,"**/**/**","siembra"))
        }

        return notaDao?.loadAllNotasMaceta(maceta_id)

    }
    fun newNota(Nota: Nota?)
    {
        notaDao?.insertNota(Nota)
    }

    fun deletNota (id: Int?){
        notaDao?.deleteFromId(id)
    }
}