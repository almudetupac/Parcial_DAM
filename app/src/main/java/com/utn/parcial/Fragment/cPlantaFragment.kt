package com.utn.parcial.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Spinner

import com.utn.parcial.R
import com.utn.parcial.Servicios.servicesPlanta


class cPlantaFragment : Fragment() {

    lateinit var v: View
    lateinit var spPlanta : Spinner
    lateinit var sPlanta : servicesPlanta


    companion object {

        fun newInstance ()= cPlantaFragment()
    }

/*
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }*/

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        v = inflater.inflate(R.layout.fragment_c_planta, container, false)
        sPlanta = servicesPlanta(v)

        spPlanta = v.findViewById(R.id.sp_planta)


        return v
    }



    override fun onStart() {
        super.onStart()

        var listaEspecies: List<String>? = sPlanta.getAllEspecies()?.toList()

        populateSpinner(spPlanta, listaEspecies!!.toList(), v.context!!)

        spPlanta.setSelection(0, false) // evita la primer falsa entrada
        spPlanta.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {

                var especie: String = listaEspecies?.get(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {

            }
        })
    }
}
