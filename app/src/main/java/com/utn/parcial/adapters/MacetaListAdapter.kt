package com.utn.parcial.adapters



import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.utn.parcial.entities.Planta
import com.utn.parcial.R
import com.utn.parcial.entities.Maceta

class MacetaListAdapter (private var macetaList: MutableList<Maceta?>,val adapterOnClick : (id : Int?) -> Unit) : RecyclerView.Adapter<MacetaListAdapter.MacetaHolder>()  {

    companion object {

        private val TAG = "MacetaListAdapter"
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MacetaHolder {
        val view =  LayoutInflater.from(parent.context).inflate(R.layout.item_maceta,parent,false)
        return (MacetaHolder(
            view
        ))
    }

    override fun getItemCount(): Int {

        return macetaList.size
    }


    override fun onBindViewHolder(holder: MacetaHolder, position: Int) {

        macetaList[position]?.name?.let { holder.setName(it) }
        holder.getCardLayout().setOnClickListener {
            adapterOnClick(macetaList[position]?.id?.toInt())
        }

    }

    class MacetaHolder (v: View) : RecyclerView.ViewHolder(v){

        private var view: View

        init {
            this.view = v
        }

        fun setName(name : String) {
            val txt : TextView = view.findViewById(R.id.txt_name_item)
            txt.text = name
        }

        fun getCardLayout (): CardView {

            return view.findViewById(R.id.card_package_item)
        }

    }
}