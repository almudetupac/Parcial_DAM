package com.utn.parcial.Fragment



import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
//import android.view.View
import android.view.*
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController

import com.utn.parcial.R
import com.utn.parcial.Servicios.serviceMaceta
import com.utn.parcial.ViewModel.NewItemViewModel
import com.utn.parcial.entities.Maceta


class NewItemFragment : Fragment() {



    lateinit var v: View
    lateinit var sMaceta : serviceMaceta
    lateinit var btnMaceta: Button
    lateinit var etxtnMaceta : EditText

    var listMacetas : MutableList<Maceta> = ArrayList()







    companion object {
        fun newInstance() = NewItemFragment()
    }

    private lateinit var viewModel: NewItemViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_new_item, container, false)
        sMaceta = serviceMaceta(v)

        btnMaceta = v.findViewById(R.id.bt_nMaceta)
        etxtnMaceta = v.findViewById(R.id.etxt_nMaceta)
        return v
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(NewItemViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onStart() {
        super.onStart()

      /*  var listaEspecies :List<String>?  = sMaceta.getAllEspecies()?.toList()

        populateSpinner(spPlanta,listaEspecies!!.toList(),v.context!!)

        spPlanta.setSelection(0, false) // evita la primer falsa entrada
        spPlanta.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {

                var especie :String = listaEspecies?.get(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {

            }
        })
*/

        btnMaceta.setOnClickListener {

            // listMacetas = NewItemFragmentArgs.fromBundle(requireArguments()).listMascotas!!.toMutableList()     // no puedo poner argument!!
            sMaceta.newMaceta(Maceta(etxtnMaceta.text.toString(), "@drawable/defoult_planta",0, ""))
            val directions = NewItemFragmentDirections.actionNewItemFragmentToListaFragment()
            v.findNavController().navigate(directions)

        }





    }

}





/*
* Aux Functions
*
* */

fun populateSpinner (spinner: Spinner, list :List<String>, context : Context)
{
    //   val aa = ArrayAdapter( context!!, android.R.layout.simple_spinner_item, list)
    val aa = ArrayAdapter(context,android.R.layout.simple_spinner_item, list)

    // Set layout to use when the list of choices appear
    aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
    // Set Adapter to Spinner
    spinner.setAdapter(aa)
}
