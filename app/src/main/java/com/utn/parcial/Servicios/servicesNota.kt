package com.utn.parcial.Servicios

import android.annotation.SuppressLint
import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import android.os.Build
import android.view.View
import androidx.annotation.RequiresApi
import com.utn.parcial.database.appDatabase
import com.utn.parcial.database.notaDao
import com.utn.parcial.entities.Nota
import java.util.*


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

            this.newNota(Nota(maceta_id, "" ,"siembra"))
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

/*
    @RequiresApi(Build.VERSION_CODES.N)
    fun obtenerFechaActual(): String? {
        val formato = "yyyy-MM-dd"
        return obtenerFechaConFormato(formato)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    @SuppressLint("SimpleDateFormat")
    fun obtenerFechaConFormato(
        formato: String?

    ): String? {
        val calendar: Calendar = Calendar.getInstance()
        val date: Date = calendar.getTime()
        val sdf: SimpleDateFormat
        sdf = SimpleDateFormat(formato)
        return sdf.format(date)
    }
*/
}