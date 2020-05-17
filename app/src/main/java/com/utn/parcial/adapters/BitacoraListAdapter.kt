package com.utn.parcial.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.utn.parcial.Fragment.BitacoraFragment
//import androidx.test.core.app.ApplicationProvider.getApplicationContext
import com.utn.parcial.R
import com.utn.parcial.ViewModel.BitacoraViewModel
import com.utn.parcial.entities.Nota
import kotlinx.android.synthetic.main.item_maceta.view.*

class BitacoraListAdapter(private var bitacoraList: MutableList<Nota?>, val adapterOnClick: (id1 : Int?) -> Unit, val adapterOnLongClick: (id : Int?) -> Boolean) : RecyclerView.Adapter<BitacoraListAdapter.BitacoraHolder>()  {
//class BitacoraListAdapter(private var bitacoraList: MutableList<Nota?>, val adapterOnClick: (pepe : Int?) -> Unit) : RecyclerView.Adapter<BitacoraListAdapter.BitacoraHolder>()  {



    companion object {

        private val TAG = "BitacoraListAdapter"
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BitacoraHolder {
        val view =  LayoutInflater.from(parent.context).inflate(R.layout.item_nota,parent,false)
        return (BitacoraHolder(
            view
        ))
    }

    override fun getItemCount(): Int {

        return bitacoraList.size
    }


    override fun onBindViewHolder(holder: BitacoraHolder, position: Int) {

        bitacoraList[position]?.fecha?.let { holder.setFecha(it) }
        bitacoraList[position]?.nota?.let { holder.setNota(it) }

        holder.getCardLayout().setOnClickListener {
            holder.setCardElevation("10")
            var id = bitacoraList[position]?.id

            adapterOnClick(id)
       //     Log.d("Adapter", bitacoraList[position]?.id.toString())
        }


        holder.getCardLayout().setOnLongClickListener {
            holder.setCardElevation("0")
            var id = bitacoraList[position]?.id

        //    Log.d("Adapter", bitacoraList[position]?.id.toString())
            adapterOnLongClick(id)
            true // <- set to true
        }
    }

    class BitacoraHolder (v: View) : RecyclerView.ViewHolder(v){

        private var view: View
        init {
            this.view = v
        }

        fun setNota(nota : String) {
            val txt : TextView = view.findViewById(R.id.txt_nota)

            txt.text = nota

        }

        fun setFecha( fecha : String) {

            val txt_fechaNota : TextView = view.findViewById(R.id.txt_fechaNota)

            txt_fechaNota.text = fecha
        }

        fun getCardLayout (): CardView {

            return view.findViewById(R.id.card_package_item)
        }

        fun setCardElevation ( elevation: String){

            val CardView_Nota : CardView = view.findViewById(R.id.card_package_item)

            CardView_Nota.cardElevation = elevation.toFloat()
        }
    }
}