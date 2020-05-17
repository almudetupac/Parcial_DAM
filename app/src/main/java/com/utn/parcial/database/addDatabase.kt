package com.utn.parcial.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.utn.parcial.entities.Maceta
import com.utn.parcial.entities.Nota
import com.utn.parcial.entities.Planta
import com.utn.parcial.entities.Foto

import com.utn.parcial.entities.User


@Database(entities = [User::class, Planta::class, Maceta::class, Nota::class, Foto::class], version = 1 , exportSchema = false)

public  abstract class appDatabase : RoomDatabase() {

    abstract fun userDao(): userDao
    abstract fun plantaDao(): plantaDao
    abstract fun macetaDao(): macetaDao
    abstract fun notaDao(): notaDao
    abstract fun fotoDao(): fotoDao

    companion object {
        var INSTANCE: appDatabase? = null

        fun getAppDataBase(context: Context): appDatabase? {
            if (INSTANCE == null) {
                synchronized(appDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        appDatabase::class.java,
                        "mjiDB"
                    ).allowMainThreadQueries().build() // No es lo mas recomendable que se ejecute en el mainthread
                }
            }
            return INSTANCE
        }

        fun destroyDataBase(){
            INSTANCE = null
        }
    }
}