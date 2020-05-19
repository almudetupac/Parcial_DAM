package com.utn.parcial.Fragment

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import androidx.lifecycle.ViewModelProvider
import com.utn.parcial.R
import com.utn.parcial.Servicios.servicesNota
import com.utn.parcial.ViewModel.BitacoraViewModel
import com.utn.parcial.ViewModel.MacetaViewModel
import com.utn.parcial.adapters.BitacoraListAdapter
import com.utn.parcial.entities.Nota

class BitacoraFragment : Fragment() {

    lateinit var v: View

    lateinit var btnAdd : FloatingActionButton
    lateinit var recNota : RecyclerView
    lateinit var sNota : servicesNota
    var listNotas : MutableList<Nota?>? = null
    private lateinit var viewModel_maceta: MacetaViewModel

    private lateinit var viewModel: BitacoraViewModel


    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var bitacoraListAdapter: BitacoraListAdapter


    companion object {
        fun newInstance() = BitacoraFragment()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        v = inflater.inflate(R.layout.fragment_bitacora, container, false)
        btnAdd = v.findViewById(R.id.btn_addBitacora)
        recNota = v.findViewById(R.id.rec_Bitacora)
        sNota = servicesNota(v)

        return v
    }
    ///////////Tolbar ////////////////

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        inflater.inflate(R.menu.toolbar_bitacora, menu)
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
        viewModel = ViewModelProvider(requireActivity()).get(BitacoraViewModel::class.java)
        viewModel_maceta = ViewModelProvider(requireActivity()).get(MacetaViewModel::class.java)
        // TODO: Use the ViewModel
    }

    var aux : Int = 0
    override fun onStart() {
        super.onStart()

        btnAdd.setOnClickListener {


            var action = macetaFragmentDirections.actionMacetaFragmentToNewNotaFragment7()
            v.findNavController().navigate(action)

        }

        viewModel_maceta.id.observe(viewLifecycleOwner, Observer { result ->

            aux = result.toInt()

            listNotas = sNota.getAllNotas(aux!!.toInt())


            if(listNotas == null){

                listNotas = ArrayList<Nota?>()

            }
            recNota.setHasFixedSize(false)

            linearLayoutManager = LinearLayoutManager(context)

            recNota.layoutManager = linearLayoutManager                                   //defino el layaut de la lista

            bitacoraListAdapter = BitacoraListAdapter(listNotas!!,{onItemClick (it)}) {onItemLongClick(it)}             //instancio el adaptador, le mando la lista, Y escucho el clic por un lamda ( puntero a funcion)

            recNota.adapter = bitacoraListAdapter                                           // cargo el adaptador
        })
    }



    public fun onItemClick (id : Int?) {

                if (viewModel.list_delet.size > 0){
                    if (id != null) {

                        for (notaActual in viewModel.list_delet){
                            //Log.d("Click", notaActual.toString())
                            if (notaActual == id){
                                viewModel.list_delet.remove(notaActual)
                            }
                        }
                    }
                }
        //Log.d("Click", list_delet.size.toString())
        //v.findNavController().navigate(listaFragmentDirections.actionListaFragmentToMacetaFragment(id!!.toInt()))
    }

    public fun onItemLongClick (id : Int?):Boolean{
        if (id != null) {
            viewModel.list_delet.add(id.toInt())
        }
        return true
    }


    private fun nav_eliminar(){
        if (viewModel.list_delet.size > 0){
       var action = macetaFragmentDirections.actionMacetaFragmentToBorrarFragment ("Nota")
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


}
