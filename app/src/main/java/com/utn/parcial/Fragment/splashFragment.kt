package com.utn.parcial.Fragment

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.utn.parcial.Activitys.HuertaActivity


import com.utn.parcial.R
import com.utn.parcial.Servicios.servicesPlanta

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [splashFragment.newInstance] factory method to
 * create an instance of this fragment.
 */






class splashFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

lateinit var sPlanta : servicesPlanta



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }
    lateinit var v: View
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_splash, container, false)

        sPlanta = servicesPlanta(v)

        return v
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment splashFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            splashFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
    private val SPLASH_TIME_OUT:Long = 2900 // 1 sec
    override fun onStart() {
        super.onStart()

        if(sPlanta.hayPlantas() == 0)
        {
            sPlanta.cargarListaPlantas()
        }


        Handler().postDelayed({
            //startActivity(Intent(v.context, HuertaActivity::class.java))
           v.findNavController().navigate(splashFragmentDirections.actionSplashFragmentToLoginFragment())

        }, SPLASH_TIME_OUT)
    }

}
