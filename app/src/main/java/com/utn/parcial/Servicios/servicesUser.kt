package com.utn.parcial.Servicios


import android.view.View
import android.widget.EditText
import com.utn.parcial.entities.User
import android.util.Log
import com.utn.parcial.database.appDatabase
import com.utn.parcial.database.userDao



class servicesUser (v : View){

    private var user : User? = null
    private var error : String =""
    private var db: appDatabase? = null
    private var userDao: userDao? = null

    init {
        db = appDatabase.getAppDataBase(v.context)
        userDao = db?.userDao()
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

    fun Login (User : EditText, password : EditText) : Boolean
    {

        if (this.userDao?.cantUser(User.text.toString()) != 0 )
        {
            if (this.userDao?.getPasswordByUser(User.text.toString()) == password.text.toString())
            {
                return true
            }
            else
            {
                this.setError("Contraseña incorrecta")
            }
        }
        else
        {
            this.setError("El Usuario no existe")
        }
        return false
    }


    fun newUser (User : EditText,  email : EditText, password: EditText) : Boolean
    {
        //var i: Int
        this.user = userDao?.loadPersonByUser(User.text.toString())
        if(this.userDao?.cantUser(User.text.toString()) == 0)
        {
            if (((email.text.toString().indexOf("@", 0, true))!=-1) &&
                ((email.text.toString().indexOf(".", 0, true))>=(email.length()-4)) &&
                ((email.text.toString().indexOf("@", 0, true))<(email.length()-6)))
            {

                if((password.length() >  7))
                {

                    //i = this.userDao?.proximaId()!!
                    //this.userDao?.insertPerson(User( i , User.text.toString(),email.text.toString(),password.text.toString()))
                    this.userDao?.insertPerson(User(User.text.toString(),email.text.toString(),password.text.toString()))
                    return true
                }
                else
                {
                    this.setError("La contraseña debe tener al menos 8 caracteres")
                }
            }
            else
            {
                this.setError("formato de email Incorrecto")
            }
        }
        else
        {
            this.setError("Nombre de usuario no disponible")
        }
        return false
    }

}