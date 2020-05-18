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
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.TedPermission
import com.utn.parcial.R
import com.utn.parcial.Servicios.serviceFoto
import com.utn.parcial.ViewModel.GaleriaViewModel
import com.utn.parcial.ViewModel.MacetaViewModel
import com.utn.parcial.adapters.GaleriaListAdapter
import com.utn.parcial.entities.Foto
import gun0912.tedbottompicker.TedBottomPicker
import io.reactivex.disposables.Disposable


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


    private var iv_image: ImageView? = null
    private var selectedUriList: List<Uri>? = null
    private val selectedUri: Uri? = null
    private val singleImageDisposable: Disposable? = null
    private val multiImageDisposable: Disposable? = null
    private var mSelectedImagesContainer: ViewGroup? = null
    private var requestManager: RequestManager? = null
    companion object {
        fun newInstance() = galeriaFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.galeria_fragment, container, false)
        im_foto = v.findViewById(R.id.im_foto)
        recFoto = v.findViewById(R.id.rec_galeria)
        sFoto = serviceFoto(v)
        iv_image = v.findViewById(R.id.im_foto)
        mSelectedImagesContainer = v.findViewById(R.id.rec_galeria)
        requestManager = Glide.with(this)
        setMultiShowButton()
        return v
    }


//    private val PERMISSIONS_READ_WRITE = 123

  //  val REQUEST_RESULT_CODE = 101


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(GaleriaViewModel::class.java)
        viewModel_maceta = ViewModelProvider(requireActivity()).get(MacetaViewModel::class.java)
        // TODO: Use the ViewModel

    }
/*

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //setContentView(R.layout.activity_huerta)
        if (isReadWritePermitted()) getGalleryResults() else checkReadWritePermission()
        val i = Intent(requireContext(), PickerActivity::class.java)
        i.putExtra("IMAGES_LIMIT", 4)
        i.putExtra("VIDEOS_LIMIT", 1)
        i.putExtra("REQUEST_RESULT_CODE", REQUEST_RESULT_CODE)
        startActivityForResult(i, 101)
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
            if (resultCode == REQUEST_RESULT_CODE && data != null) {
                val mediaList = data.getParcelableArrayListExtra<GalleryData>("MEDIA")
                MLog.e("SELECTED MEDIA", mediaList.size.toString())
                //Log.d("uri:",Uri)
                Log.d("uri:",mediaList[0].toString())
                Log.d("uri:",Uri.encode(mediaList[0].photoUri))
               /*Glide.with(this)
                  .load(mediaList[0].photoUri)
                  .fitCenter()
                  .centerCrop()
                  .into(iv)

               */

            //iv.setImageURI("/storage/emulated/0/WhatsApp/Media/WhatsApp Images/IMG-20200514-WA0012.jpeg")
        }
    }
    fun getGalleryResults() {
        val images = GalleryPicker(requireContext()).getImages()
        val videos = GalleryPicker(requireContext()).getVideos()
        //text.text = images[0].DATA

    }
    @RequiresApi(Build.VERSION_CODES.M)
    fun checkReadWritePermission(): Boolean {
        requestPermissions(arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE), PERMISSIONS_READ_WRITE)
        return true
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            PERMISSIONS_READ_WRITE -> if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) getGalleryResults()
        }
    }

   private fun isReadWritePermitted(): Boolean = checkPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                                            && checkPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE)
                                            && checkPermission(android.Manifest.permission.CAMERA)


    private fun checkPermission(permission: String): Boolean {

        val res = requireContext().checkCallingOrSelfPermission(permission)
        return res == PackageManager.PERMISSION_GRANTED
    }

*/


    private fun setMultiShowButton() {
        val btnMultiShow: Button = v.findViewById(R.id.bt_2)
        btnMultiShow.setOnClickListener { view: View? ->
            val permissionlistener: PermissionListener = object : PermissionListener {
                override fun onPermissionGranted() {
                    TedBottomPicker.with(activity).setPeekHeight(getResources().getDisplayMetrics().heightPixels/2)
                        .setPeekHeight(1600)
                        .showTitle(true)
                        .setCompleteButtonText("Done")
                        .setEmptySelectionText("No Select")
                        .setSelectedUriList(selectedUriList)
                        .showMultiImage { uriList: List<Uri> ->
                            selectedUriList = uriList
                            showUriList(uriList)
                        }
                }

                override fun onPermissionDenied(deniedPermissions: java.util.ArrayList<String?>) {
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



    private fun showUriList(uriList: List<Uri>) {

        var selectedUri: Uri = uriList[0]

        sFoto.newFoto(Foto(aux,   selectedUri.toString()))
        // Remove all views before
        // adding the new ones.
        mSelectedImagesContainer!!.removeAllViews()
        iv_image!!.visibility = View.GONE
        mSelectedImagesContainer!!.visibility = View.VISIBLE
        val widthPixel = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            100f,
            resources.displayMetrics
        ).toInt()
        val heightPixel = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            100f,
            resources.displayMetrics
        ).toInt()
        for (uri in uriList) {
            val imageHolder: View = LayoutInflater.from(context).inflate(R.layout.item_foto, null)
            val thumbnail = imageHolder.findViewById<ImageView>(R.id.im_foto)
            requestManager
                ?.load(uri.toString())
                ?.apply(RequestOptions().fitCenter())
                ?.into(thumbnail)
            mSelectedImagesContainer!!.addView(imageHolder)
            thumbnail.layoutParams = FrameLayout.LayoutParams(widthPixel, heightPixel)
        }
    }

     override fun onDestroy() {
        if (singleImageDisposable != null && !singleImageDisposable.isDisposed) {
            singleImageDisposable.dispose()
        }
        if (multiImageDisposable != null && !multiImageDisposable.isDisposed) {
            multiImageDisposable.dispose()
        }
        super.onDestroy()
    }











    ////////////////////////////////////



     var aux : Int = 0

    override fun onStart() {
        super.onStart()

        viewModel_maceta.id.observe(viewLifecycleOwner, Observer { result ->

            aux = result.toInt()

           // listFotos = sFoto.getAllFotos(aux.toInt())
        })


/*

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
   */ }

}
