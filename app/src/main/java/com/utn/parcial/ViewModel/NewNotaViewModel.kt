package com.utn.parcial.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NewNotaViewModel : ViewModel() {

    var input : MutableLiveData<String> = MutableLiveData<String>()
}
