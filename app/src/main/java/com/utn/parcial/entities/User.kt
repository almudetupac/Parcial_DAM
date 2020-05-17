package com.utn.parcial.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import kotlin.properties.Delegates


@Entity(tableName = "users")
class User (user : String , email : String, password: String) {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int? = null


    @ColumnInfo(name = "user")
    var user : String

    @ColumnInfo(name = "email")
    var email : String

    @ColumnInfo(name = "password")
    var password : String


    init {

        this.user = user
        this.email = email
        this.password = password
    }
}




















/*




class User {


    class Personas{
        companion object {
            var personas : MutableList<Persona> = ArrayList<Persona>()

            init {
                personas.add(Persona("tupac","almude.tupac@gmail.com","12345678"))
            }
        }
    }




    /// retorna true si el usuario existe

    fun getUser(User : String) : Persona
    {
        for (personaActual in Personas.personas) {
            if (personaActual.user == User) {
                return personaActual
            }
        }
        var persona = Persona("","","")
        return persona
    }

    fun getPassword (User : String) : String
    {
        for (personaActual in Personas.personas) {
            if (personaActual.user == User) {
                return personaActual.password
            }
        }
        return "no existe"  //como lo comprobe que existe el user antes nunca deveria llegar aca
    }


    fun getEmail (User : String) : String
    {
        for (personaActual in Personas.personas) {
            if (personaActual.user == User) {
                return personaActual.email
            }
        }
        return "no existe"  //como lo comprobe que existe el user antes nunca deveria llegar aca
    }


    fun setUser(User : String, NewUser :String) : Boolean
    {
        for (personaActual in Personas.personas) {
            if (personaActual.user == User) {
                personaActual.user == NewUser
                return true
            }
        }
        return false
    }


    fun setPassword (User : String, NewPassword :String) : Boolean
    {
        for (personaActual in Personas.personas) {
            if (personaActual.user == User) {
                personaActual.password == NewPassword
                return true
            }
        }
        return false
    }

    fun setEmail (User : String, NewEmail :String) : Boolean
    {
        for (personaActual in Personas.personas) {
            if (personaActual.user == User) {
                personaActual.password == NewEmail
                return true
            }
        }
        return false
    }


    fun newUser(User: String, email: String, password: String)
    {
        Personas.personas.add(Persona(User,email,password))

    }

    fun availableUser( User: String ):Boolean {

        for (personaActual in Personas.personas) {
            if (personaActual.user == User) {
                return false
            }
        }
        return true
    }



}*/