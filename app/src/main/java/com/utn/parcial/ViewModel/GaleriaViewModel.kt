package com.utn.parcial.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GaleriaViewModel : ViewModel() {
    // TODO: Implement the ViewModel
    val list_delet : MutableList<Int> =  mutableListOf()
    val id_central = MutableLiveData<Int>()

}
