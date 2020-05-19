package com.utn.parcial.Fragment

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar


import com.utn.parcial.R
import com.utn.parcial.Servicios.serviceMaceta
import com.utn.parcial.ViewModel.ListViewModel
import com.utn.parcial.ViewModel.MacetaViewModel
import com.utn.parcial.adapters.MacetaListAdapter
import com.utn.parcial.entities.Maceta



class listaFragment : Fragment() {


    lateinit var v: View

    lateinit var btnAdd : FloatingActionButton

    lateinit var recMaceta : RecyclerView
    lateinit var sMaceta : serviceMaceta

    var listMacetas : MutableList<Maceta?>? = null


    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var macetaListAdapter: MacetaListAdapter




    companion object {
        fun newInstance() = listaFragment()
    }

    private lateinit var viewModel: ListViewModel
    private lateinit var viewModel_maceta: MacetaViewModel



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v =  inflater.inflate(R.layout.fragment_lista, container, false)
        btnAdd = v.findViewById(R.id.btn_add)
        recMaceta = v.findViewById(R.id.rec_huerta)
        sMaceta = serviceMaceta(v)
        return v
    }



    ///////////Tolbar ////////////////


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        inflater.inflate(R.menu.toolbar_lista, menu)
        super.onCreateOptionsMenu(menu, inflater)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val id = when(item.itemId) {

            R.id.action_delete -> nav_eliminar()

            R.id.action_atras -> Vaciar_lDelete()

            else -> ""
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

/////////////////////////////////////


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(ListViewModel::class.java)
        viewModel_maceta = ViewModelProvider(requireActivity()).get(MacetaViewModel::class.java)

        // TODO: Use the ViewModel
    }

    override fun onStart() {
        super.onStart()

        btnAdd.setOnClickListener {
            var action = listaFragmentDirections.actionListaFragmentToNewItemFragment()//listMacetas?.toTypedArray())
            v.findNavController().navigate(action)
        }
        listMacetas = sMaceta.getAllMacetas()

        if(listMacetas == null){
            listMacetas = ArrayList<Maceta?>()
        }


        recMaceta.setHasFixedSize(true)


        linearLayoutManager = LinearLayoutManager(context)
        recMaceta.layoutManager = linearLayoutManager                                   //defino el layaut de la lista

        macetaListAdapter = MacetaListAdapter(listMacetas!!, { onItemClick(it) }) { onItemLongClick(it) }             //instancio el adaptador, le mando la lista, Y escucho el clic por un lamda ( puntero a funcion)
        recMaceta.adapter = macetaListAdapter                                           // cargo el adaptador


    }

    public fun onItemClick (id : Int?){

        if (viewModel.list_delet.size > 0){
            for (fotoActual in viewModel.list_delet){
                //Log.d("Click", notaActual.toString())
                if (fotoActual == id!!){
                    viewModel.list_delet.remove(fotoActual)
                }
            }

        }
        else {

            viewModel_maceta.id.value = id!!.toString()
            Log.d("id", id!!.toString())
            v.findNavController().navigate(listaFragmentDirections.actionListaFragmentToMacetaFragment(id.toInt()))
        }
    }


    public fun onItemLongClick (id : Int?):Boolean{
        if (id != null) {
            viewModel.list_delet.add(id.toInt())
        }
        //Log.d("LongClick", list_delet.size.toString())
        //v.findNavController().navigate(listaFragmentDirections.actionListaFragmentToMacetaFragment(id!!.toInt()))
        return true
    }


    private fun nav_eliminar(){
        if (viewModel.list_delet.size > 0){
            var action = listaFragmentDirections.actionListaFragmentToBorrarFragment("Maceta")
            v.findNavController().navigate(action)}
    }

    private fun Vaciar_lDelete(){
        for (fotoActual in viewModel.list_delet) {
            //Log.d("Click", notaActual.toString())
            if (fotoActual == id!!) {
                viewModel.list_delet.remove(fotoActual)
            }
        }
    }



    /*
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_lista, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment listaFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            listaFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }*/
}
