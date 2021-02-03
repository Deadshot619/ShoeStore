package com.udacity.shoestore.utils

import android.app.Activity
import android.content.Context
import android.widget.Toast
import androidx.core.content.edit
import androidx.fragment.app.Fragment
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.udacity.shoestore.ShoeStoreApp
import com.udacity.shoestore.models.LoginDetail
import com.udacity.shoestore.models.Shoe

private const val SHOES_DATA = "shoes_data"

private var toast: Toast? = null

inline fun <reified T> Gson.fromJson(value: String): T =
    Gson().fromJson<T>(value, object : TypeToken<T>() {}.type)

fun Activity.showToast(msg: String, lengthLong: Boolean = false){
    toast?.cancel()
    toast = Toast.makeText(this, msg, if (lengthLong) Toast.LENGTH_LONG else Toast.LENGTH_SHORT)
    toast?.show()
}

fun Fragment.showToast(msg: String, lengthLong: Boolean = false){
    activity?.showToast(msg, lengthLong)
}

/**
 * Method to prepare Shoe List Data
 */
private fun prepareShoeListData(): List<Shoe> {
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
    return tempShoeList
}

fun getShoeListData(): List<Shoe>{
    var data = Gson().fromJson<List<Shoe>>(ShoeStoreApp.sharedPref?.getString(SHOES_DATA, "[]") ?: "[]")
    if (data.isNullOrEmpty()) {
        data = prepareShoeListData()
        saveShoeListData(data)
    }
    return data
}

private fun saveShoeListData(data: List<Shoe>) =  ShoeStoreApp.sharedPref?.edit {
    putString(SHOES_DATA, Gson().toJson(data))
}

fun addShoeListData(data: Shoe){
    saveShoeListData(getShoeListData().toMutableList().apply{ add(data) })
}