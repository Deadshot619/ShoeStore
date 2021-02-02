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
                    description = "Description $i"
                )
            )
        }
        _shoeListData.value = tempShoeList
    }
}