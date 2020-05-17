package com.utn.parcial.Fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.navigation.findNavController
import com.google.android.material.snackbar.Snackbar
import com.utn.parcial.Activitys.HuertaActivity


import com.utn.parcial.R
import com.utn.parcial.Servicios.servicesUser
import com.utn.parcial.database.appDatabase
import com.utn.parcial.entities.User
import com.wajahatkarim3.roomexplorer.RoomExplorer
import kotlin.math.log


class LoginFragment : Fragment() {

    lateinit var v: View
    lateinit var etxtpassword : EditText
    lateinit var etxtusuario : EditText
    lateinit var btnUsuario : TextView
    lateinit var btInit: Button
    lateinit var user:User
    lateinit var sUser: servicesUser
    lateinit var btnDebug: Button


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_login, container, false)

        btInit = v.findViewById(R.id.bt_init)
        btnUsuario = v.findViewById<TextView>(R.id.bt_nUsuario)
        etxtusuario = v.findViewById(R.id.etxt_usuario)
        etxtpassword = v.findViewById(R.id.etxt_password)
        btnDebug = v.findViewById(R.id.btn_Debug)
        user = User("","","")
        sUser = servicesUser (v)


        return v
    }



    override fun onStart() {
        super.onStart()

        btInit.setOnClickListener {
            if (etxtusuario.length()>0)
            {
                if(etxtpassword.length()>0)
                {
Log.d("log","1")
                    if (sUser.Login(etxtusuario, etxtpassword ))
                    {
                        startActivity(Intent(v.context, HuertaActivity::class.java))
                        getActivity()?.finish()
                    }
                    else
                    {
                        Snackbar.make ( it,sUser.getError(), Snackbar.LENGTH_SHORT).show()
                    }
                }
                else
                {
                    Snackbar.make ( it,"Ingrese Contraseña", Snackbar.LENGTH_SHORT).show()
                }
            }
            else
            {
                Snackbar.make ( it,"Ingrese un usuario", Snackbar.LENGTH_SHORT).show()
            }
        }
        btnUsuario.setOnClickListener{
            v.findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToRegisterFragment())
        }

        btnDebug.setOnClickListener {
            RoomExplorer.show(context, appDatabase::class.java, "mjiDB")



        }

    }


}
