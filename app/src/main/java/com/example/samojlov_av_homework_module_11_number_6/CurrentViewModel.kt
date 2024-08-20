package com.example.samojlov_av_homework_module_11_number_6

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CurrentViewModel: ViewModel() {
    var counter = 0
    val currentCounter: MutableLiveData<Int> by lazy { MutableLiveData<Int>() }
}