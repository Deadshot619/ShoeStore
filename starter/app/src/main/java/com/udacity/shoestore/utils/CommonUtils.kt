package com.udacity.shoestore.utils

import android.app.Activity
import android.content.Context
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

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

