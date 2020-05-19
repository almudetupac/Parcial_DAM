package com.utn.parcial.dialogs
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.github.nikartm.button.FitButton


import com.utn.parcial.R
import com.utn.parcial.Servicios.serviceFoto
import com.utn.parcial.Servicios.serviceMaceta
import com.utn.parcial.Servicios.servicesNota
import com.utn.parcial.ViewModel.BitacoraViewModel
import com.utn.parcial.ViewModel.BorrarViewModel
import com.utn.parcial.ViewModel.GaleriaViewModel
import com.utn.parcial.ViewModel.ListViewModel

class BorrarFragment : DialogFragment() {

    lateinit var v: View

    lateinit var btnAccept: FitButton
    lateinit var btnCancel: FitButton
    lateinit var sNota: servicesNota
    lateinit var sFoto: serviceFoto
    lateinit var sMaceta: serviceMaceta

    companion object {
        fun newInstance() = BorrarFragment()
    }

    private lateinit var viewModel: BorrarViewModel
    private lateinit var viewModel_Bitacora: BitacoraViewModel
    private lateinit var viewModel_Galeria: GaleriaViewModel
    private lateinit var viewModel_Lista: ListViewModel



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        v = inflater.inflate(R.layout.borrar_fragment, container, false)
        btnAccept = v.findViewById(R.id.btn_acept_dialog)
        btnCancel = v.findViewById(R.id.btn_cancel_dialog)
        sFoto = serviceFoto(v)
        sMaceta = serviceMaceta(v)
        sNota = servicesNota(v)
        return v
    }


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return dialog
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(BorrarViewModel::class.java)
        viewModel_Bitacora = ViewModelProvider(requireActivity()).get(BitacoraViewModel::class.java)
        viewModel_Galeria = ViewModelProvider(requireActivity()).get(GaleriaViewModel::class.java)
        viewModel_Lista = ViewModelProvider(requireActivity()).get(ListViewModel::class.java)
    }


    override fun onStart() {
        super.onStart()


        btnCancel.setOnClickListener() {
            dismiss()
        }





        btnAccept.setOnClickListener {

                Eliminar()
                dismiss()

        }
    }


    fun Eliminar(){

        if (BorrarFragmentArgs.fromBundle(requireArguments()).tabla == "Nota"){
            for (notaActual in viewModel_Bitacora.list_delet){
                sNota.deletNota(notaActual)
            }
        }

        if (BorrarFragmentArgs.fromBundle(requireArguments()).tabla == "Foto"){
            for (fotoActual in viewModel_Galeria.list_delet){
                sFoto.deletFoto(fotoActual)
            }
        }

        if (BorrarFragmentArgs.fromBundle(requireArguments()).tabla == "Maceta"){
            for (macetaActual in viewModel_Lista.list_delet){
                sMaceta.deletMaceta(macetaActual)
            }
        }


    }
}

