package com.utn.parcial.Fragment


//import android.support.v7.app.AppCompatActivity

/*import com.picker.gallery.model.GalleryData
import com.picker.gallery.model.interactor.GalleryPicker
import com.picker.gallery.utils.MLog
import com.picker.gallery.view.PickerActivity
*/
import android.Manifest
import android.net.Uri
import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.TedPermission
import com.utn.parcial.R
import com.utn.parcial.Servicios.serviceFoto
import com.utn.parcial.ViewModel.GaleriaViewModel
import com.utn.parcial.ViewModel.MacetaViewModel
import com.utn.parcial.adapters.GaleriaListAdapter
import com.utn.parcial.entities.Foto
import gun0912.tedbottompicker.TedBottomPicker
import java.util.ArrayList


class galeriaFragment : Fragment() {

    lateinit var im_foto: ImageView
    lateinit var recFoto: RecyclerView
    lateinit var sFoto: serviceFoto
    lateinit var v: View
    var listFotos: MutableList<Foto?>? = null

    private lateinit var viewModel_maceta: MacetaViewModel
    private lateinit var viewModel: GaleriaViewModel
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var galeriaListAdapter: GaleriaListAdapter


    private lateinit var iv_image: ImageView
    private  var selectedUriList: List<Uri>? = null
    private  var selectedUri: Uri? = null
    private var requestManager: RequestManager? = null

    var aux : Int = 0

    companion object {
        fun newInstance() = galeriaFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.galeria_fragment, container, false)
        im_foto = v.findViewById(R.id.im_tem)
        recFoto = v.findViewById(R.id.rec_galeria)
        sFoto = serviceFoto(v)
        iv_image = v.findViewById(R.id.im_tem)
        //mSelectedImagesContainer = v.findViewById(R.id.rec_galeria)
    //   requestManager = Glide.with(this)
        setMultiShowButton()


        return v
    }


    ///////////Tolbar ////////////////


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        inflater.inflate(R.menu.toolbar_galeria, menu)
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
        viewModel = ViewModelProvider(requireActivity()).get(GaleriaViewModel::class.java)
        viewModel_maceta = ViewModelProvider(requireActivity()).get(MacetaViewModel::class.java)
        // TODO: Use the ViewModel

    }



    private fun setMultiShowButton() {
        val btnMultiShow: FloatingActionButton = v.findViewById(R.id.btn_btFoto)
        btnMultiShow.setOnClickListener { view: View? ->
            val permissionlistener: PermissionListener = object : PermissionListener {
                override fun onPermissionGranted() {
                    TedBottomPicker.with(activity)//.setPeekHeight(getResources().getDisplayMetrics().heightPixels/2)
                        .setPeekHeight(1600)
                        .showTitle(true)
                        .setTitle("Seleccione Fotos")
                        .setCompleteButtonText("Cargar")
                        .setEmptySelectionText("No Select")
                        //.setCameraTileBackgroundResId("@color/toolbar".toInt())
                        //.setCameraTileBackgroundResId(1)
                        .setSelectedUriList(selectedUriList)
                        .showMultiImage { uriList: List<Uri> ->
                            selectedUriList = uriList
                            GuardarUriList(uriList)
                        }
                }

                override fun onPermissionDenied(deniedPermissions: ArrayList<String?>) {
                    Toast.makeText(
                        context,
                        "Permission Denied\n$deniedPermissions",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
            checkPermission(permissionlistener)
        }
    }

    private fun checkPermission(permissionlistener: PermissionListener) {
        TedPermission.with(context)
            .setPermissionListener(permissionlistener)
            .setDeniedMessage("If you reject permission,you can not use this service\n\nPlease turn on permissions at [Setting] > [Permission]")
            .setPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE)
            .check()
    }



    private fun GuardarUriList(uriList: List<Uri>) {

        for (uri in uriList) {
            sFoto.newFoto(Foto(aux, uri.toString()))
        }
    }


    private fun Vaciar_lDelete(){
        for (fotoActual in viewModel.list_delet) {
            //Log.d("Click", notaActual.toString())
            if (fotoActual == id!!) {
                viewModel.list_delet.remove(fotoActual)
            }
        }
    }






    ////////////////////////////////////





    override fun onStart() {
        super.onStart()





        viewModel_maceta.id.observe(viewLifecycleOwner, Observer { result ->

            aux = result.toInt()

            listFotos = sFoto.getAllFotos(aux)

            Glide.with(v)
                .load(sFoto.getUltimaFoto(aux))
                .fitCenter()
                .centerCrop()
                .into(im_foto)



            if(listFotos != null) {

                // listFotos = ArrayList<Foto?>()

                //  }


                recFoto.setHasFixedSize(false)


                linearLayoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)

                recFoto.layoutManager = linearLayoutManager                                   //defino el layaut de la lista

                galeriaListAdapter = GaleriaListAdapter(listFotos!!, { onItemClick(it) }) { onItemLongClick(it) }             //instancio el adaptador, le mando la lista, Y escucho el clic por un lamda ( puntero a funcion)

                    recFoto.adapter = galeriaListAdapter                                           // cargo el adaptador
            }
        })

    }



    public fun onItemClick (id : Int?) {
        //Log.d("Click_Id", id.toString())

        if (viewModel.list_delet.size > 0){
            for (fotoActual in viewModel.list_delet){
                //Log.d("Click", notaActual.toString())
                if (fotoActual == id!!){
                    viewModel.list_delet.remove(fotoActual)
                }
            }

        }
        else{

            viewModel.id_central.value = id!!
            Glide.with(v)
                .load(sFoto.getFotoId(id))
                .fitCenter()
                .centerCrop()
                .into(im_foto)
        }

    }

    public fun onItemLongClick (id : Int?):Boolean{
        if (id != null) {
            viewModel.list_delet.add(id.toInt())
        }

        return true
    }
    private fun nav_eliminar(){
        if (viewModel.list_delet.size > 0){
            var action = macetaFragmentDirections.actionMacetaFragmentToBorrarFragment ("Foto")
            v.findNavController().navigate(action)}
    }
}
