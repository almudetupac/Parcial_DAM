package com.utn.parcial.Fragment

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
//import com.gun0912.tedpermission.PermissionListener
import com.utn.parcial.R
import com.utn.parcial.Servicios.serviceFoto
import com.utn.parcial.ViewModel.GaleriaViewModel
import com.utn.parcial.ViewModel.MacetaViewModel
import com.utn.parcial.adapters.GaleriaListAdapter
import com.utn.parcial.entities.Foto
//import gun0912.tedbottompicker.TedBottomPicker
//import io.reactivex.disposables.Disposable


//import android.support.v7.app.AppCompatActivity

import java.util.ArrayList



class galeriaFragment : Fragment() {

    lateinit var im_foto: ImageView
    lateinit var recFoto : RecyclerView
    lateinit var sFoto : serviceFoto
    lateinit var v: View
    var listFotos : MutableList<Foto?>? = null
    private lateinit var viewModel_maceta: MacetaViewModel
    private lateinit var viewModel: GaleriaViewModel
/*
    private var selectedUriList: List<Uri>? = null
    private val selectedUri: Uri? = null
    private val singleImageDisposable: Disposable? = null
    private val multiImageDisposable: Disposable? = null
    private val mSelectedImagesContainer: ViewGroup? = null
    private val requestManager: RequestManager? = null

*/
    companion object {
        fun newInstance() = galeriaFragment()
    }

    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var galeriaListAdapter: GaleriaListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.galeria_fragment, container, false)
        im_foto = v.findViewById(R.id.im_foto)
        recFoto = v.findViewById(R.id.rec_galeria)
        sFoto = serviceFoto(v)
        return v
    }

/*
    private fun setMultiShowButton() {
        val btnMultiShow: Button = v.findViewById(R.id.bt_2)
        btnMultiShow.setOnClickListener { view: View? ->
            val permissionlistener: PermissionListener = object : PermissionListener {
                override fun onPermissionGranted() {
                    TedBottomPicker.with(requireActivity()) //.setPeekHeight(getResources().getDisplayMetrics().heightPixels/2)
                        .setPeekHeight(1600)
                        .showTitle(false)
                        .setCompleteButtonText("Seleccionar")
                        .setEmptySelectionText("No Select")
                        .setSelectedUriList(selectedUriList)
                        .showMultiImage { uriList: List<Uri?> ->
                            selectedUriList = uriList
                            showUriList(uriList)
                        }
                }

                override fun onPermissionDenied(deniedPermissions: java.util.ArrayList<String>) {
                    Toast.makeText(
                        this@MainActivity,
                        "Permission Denied\n$deniedPermissions",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
            checkPermission(permissionlistener)
        }
    }

*/


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(GaleriaViewModel::class.java)
        viewModel_maceta = ViewModelProvider(requireActivity()).get(MacetaViewModel::class.java)
        // TODO: Use the ViewModel
    }
     var aux : Int = 0

    override fun onStart() {
        super.onStart()

        viewModel_maceta.id.observe(viewLifecycleOwner, Observer { result ->

            aux = result.toInt()

            listFotos = sFoto.getAllFotos(aux.toInt())
        })




        if(listFotos == null){

            listFotos = ArrayList<Foto?>()

        }


        recFoto.setHasFixedSize(true)

        linearLayoutManager = LinearLayoutManager(context)

        recFoto.layoutManager = linearLayoutManager                                   //defino el layaut de la lista

        galeriaListAdapter = GaleriaListAdapter(listFotos!!,{onItemClick (it)}) {onItemLongClick(it)}             //instancio el adaptador, le mando la lista, Y escucho el clic por un lamda ( puntero a funcion)

        recFoto.adapter = galeriaListAdapter                                           // cargo el adaptador

    }



    public fun onItemClick (id : Int?) {
        //Log.d("Click_Id", id.toString())
        if (viewModel.list_delet.size > 0){
            if (id != null) {

                for (notaActual in viewModel.list_delet){
                    //Log.d("Click", notaActual.toString())
                    if (viewModel.list_delet[notaActual] == id){
                        viewModel.list_delet.removeAt(notaActual)
                    }
                }
            }
        }

    }

    public fun onItemLongClick (id : Int?):Boolean{
        if (id != null) {
            viewModel.list_delet.add(id.toInt())
        }

        return true
    }

}
