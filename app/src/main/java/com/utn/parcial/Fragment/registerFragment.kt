package com.utn.parcial.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.navigation.findNavController
import com.google.android.material.snackbar.Snackbar

import com.utn.parcial.R
import com.utn.parcial.Servicios.servicesUser
import com.utn.parcial.entities.User


class registerFragment : Fragment() {

    private val SPLASH_TIME_OUT:Long = 3000 // 1 sec


    lateinit var v : View
    lateinit var etxtuser : EditText
    lateinit var etxtemail : EditText
    lateinit var etxtpass : EditText
    lateinit var etxtpass1 : EditText
    lateinit var btcrear : Button
    lateinit var user: User
    lateinit var sUser: servicesUser


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment

        v = inflater.inflate(R.layout.fragment_register, container, false)
        etxtuser = v.findViewById(R.id.etxt_user)
        etxtemail = v.findViewById(R.id.etxt_email)
        etxtpass = v.findViewById(R.id.etxt_pass)
        etxtpass1= v.findViewById(R.id.etxt_pass1)
        btcrear = v.findViewById(R.id.bt_crear)
        user = User("","","")
        sUser = servicesUser (v)

        return v
    }

    override fun onStart() {
        super.onStart()

        btcrear.setOnClickListener{
            if ((etxtuser.length() > 0) && (etxtemail.length() > 0)  && (etxtpass.length() > 0) && (etxtpass1.length() > 0) )
            {
                if (etxtpass.text.toString() == etxtpass1.text.toString()) {

                    if(sUser.newUser(etxtuser, etxtemail, etxtpass ))
                    {
                        v.findNavController().navigate(registerFragmentDirections.actionRegisterFragmentToLoginFragment())
                    }
                    else
                    {
                        Snackbar.make ( it,sUser.getError(), Snackbar.LENGTH_SHORT).show()
                    }
                }
                else {

                    Snackbar.make(it, "Las contraseñas no coinsiden", Snackbar.LENGTH_SHORT).show()
                }
            }
            else
            {
                Snackbar.make ( it,"Complete todo los campos", Snackbar.LENGTH_SHORT).show()
            }
        }
    }
}
