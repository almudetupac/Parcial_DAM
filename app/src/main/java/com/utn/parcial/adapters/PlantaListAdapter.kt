package com.utn.parcial.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.utn.parcial.entities.Planta
import com.utn.parcial.R
/*
//class PlantaListAdapter(private var plantasList: MutableList<Planta>,val adapterOnClick : () -> Unit) : RecyclerView.Adapter<PlantaListAdapter.PlantaHolder>()  {

    companion object {

        private val TAG = "MascotaListAdapter"
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlantaHolder {
        val view =  LayoutInflater.from(parent.context).inflate(R.layout.item_maceta,parent,false)
        return (PlantaHolder(
            view
        ))
    }

    override fun getItemCount(): Int {

        return plantasList.size
    }

//    fun setData(newData: ArrayList<Mascota>) {
//        this.mascotasList = newData
//        this.notifyDataSetChanged()
//    }

    override fun onBindViewHolder(holder: PlantaHolder, position: Int) {
/*
        holder.setEspecie(plantasList[position].especie)
        holder.getCardLayout().setOnClickListener {
            adapterOnClick()*/
        }

    }

    class PlantaHolder (v: View) : RecyclerView.ViewHolder(v){

        private var view: View

        init {
            this.view = v
        }
/*
        fun setEspecie(especie : String) {
            val txt : TextView = view.findViewById(R.id.txt_especie_item)
            txt.text = especie
        }*/

        fun getCardLayout (): CardView {

            return view.findViewById(R.id.card_package_item)
        }

    }
}*///