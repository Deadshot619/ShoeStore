package com.udacity.shoestore

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.utils.getShoeListData

class MainViewModel: ViewModel() {

    private val _shoeListData = MutableLiveData<List<Shoe>>()
    val shoeListData: LiveData<List<Shoe>>
        get() = _shoeListData

    init {
        _shoeListData.value = getShoeListData()
    }
}