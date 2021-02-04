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

    val shoeDetail = Shoe(
        name = "",
        size = 0.0,
        company = "",
        description = "",
        images = listOf("https://images.unsplash.com/photo-1460353581641-37baddab0fa2?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=1351&q=80")
    )

    init {
        refreshShoeListData()
    }

    fun refreshShoeListData(){
        _shoeListData.value = getShoeListData()
    }
}