package com.utn.parcial.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.utn.parcial.R
import com.utn.parcial.entities.Foto

class GaleriaListAdapter (private var galeriaList: MutableList<Foto?>, val adapterOnClick: (id1 : Int?) -> Unit, val adapterOnLongClick: (id : Int?) -> Boolean) : RecyclerView.Adapter<GaleriaListAdapter.GaleriaHolder>()  {
    companion object {

        private val TAG = "BitacoraListAdapter"
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GaleriaHolder {
        val view =  LayoutInflater.from(parent.context).inflate(R.layout.item_foto,parent,false)
        return (GaleriaHolder(
            view
        ))
    }

    override fun getItemCount(): Int {

        return galeriaList.size
    }


    override fun onBindViewHolder(holder: GaleriaHolder, position: Int) {

        galeriaList[position]?.foto?.let { holder.setFecha(it) }

        holder.getCardLayout().setOnClickListener {
            holder.setCardElevation("10")
            var id = galeriaList[position]?.id

            adapterOnClick(id)

        }


        holder.getCardLayout().setOnLongClickListener {
            holder.setCardElevation("0")
            var id = galeriaList[position]?.id
            adapterOnLongClick(id)
            true // <- set to true
        }
    }

    class GaleriaHolder (v: View) : RecyclerView.ViewHolder(v){

        private var view: View
        init {
            this.view = v
        }

        fun setFoto(foto : String) {
            val im_foto : ImageView = view.findViewById(R.id.im_foto)

            //im_foto.setImageDrawable(@drawable/)

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