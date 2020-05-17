package com.utn.parcial.dialogs


import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.github.nikartm.button.FitButton
import com.google.android.material.snackbar.Snackbar
import com.utn.parcial.ViewModel.MacetaViewModel

import com.utn.parcial.ViewModel.NewNotaViewModel


import com.utn.parcial.R
import com.utn.parcial.Servicios.servicesNota
import com.utn.parcial.entities.Nota


class newNotaFragment : DialogFragment() {

    lateinit var v: View

    lateinit var edt_nota: EditText
    lateinit var btnAccept: FitButton
    lateinit var btnCancel: FitButton
    lateinit var sNota: servicesNota
    private lateinit var viewModel_maceta: MacetaViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        Log.d("nota","1")
        v = inflater.inflate(R.layout.fragment_new_nota, container, false)
        Log.d("nota","2")
        edt_nota = v.findViewById(R.id.edt_nota_dialog)
        btnAccept = v.findViewById(R.id.btn_acept_dialog)
        btnCancel = v.findViewById(R.id.btn_cancel_dialog)
        sNota = servicesNota(v)
        return v

    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        Log.d("nota","3")
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return dialog
    }

    var aux : Int = 0
    override fun onStart() {
        super.onStart()

        val newNotaViewModel = ViewModelProvider(requireActivity()).get(NewNotaViewModel::class.java)


        viewModel_maceta.id.observe(viewLifecycleOwner, Observer { result ->

            aux = result.toInt()

        })

        Log.d("nota","5")
        btnCancel.setOnClickListener() {
            dismiss()
        }





        btnAccept.setOnClickListener {
            if (edt_nota.length() > 0) {

                sNota.newNota(Nota(aux,"",   edt_nota.text.toString()))
                newNotaViewModel.input.value = edt_nota.text.toString()
                dismiss()
            } else {
                Snackbar.make(
                    v, "campo vacio", Snackbar.LENGTH_SHORT
                ).show()
            }
        }
    }







    companion object {
        fun newInstance() = newNotaFragment()
    }

    private lateinit var viewModel: NewNotaViewModel



    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel =  ViewModelProvider(requireActivity()).get(NewNotaViewModel::class.java)
        viewModel_maceta = ViewModelProvider(requireActivity()).get(MacetaViewModel::class.java)


    }

}
