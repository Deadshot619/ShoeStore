package com.udacity.shoestore.ui.shoelist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class ShoeListViewModel : ViewModel() {

    private val _shoeListData = MutableLiveData<List<Shoe>>()
    val shoeListData: LiveData<List<Shoe>>
        get() = _shoeListData

    init {
        prepareShoeListData()
    }

    /**
     * Method to prepare Shoe List Data
     */
    private fun prepareShoeListData() {
        val tempShoeList = mutableListOf<Shoe>()
        for (i in 1..10) {
            tempShoeList.add(
                Shoe(
                    name = "Shoe $i",
                    size = (i * 2).toDouble(),
                    company = "Company $i",
                    description = "Description $i",
                    images = listOf("https://images.unsplash.com/photo-1460353581641-37baddab0fa2?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=1351&q=80")
                )
            )
        }
        _shoeListData.value = tempShoeList
    }
}