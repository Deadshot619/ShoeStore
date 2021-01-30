package com.udacity.shoestore.utils

import android.content.SharedPreferences
import android.widget.Toast
import androidx.core.content.edit
import com.google.gson.Gson
import com.udacity.shoestore.ShoeStoreApp
import com.udacity.shoestore.models.LoginDetail

private const val LOGIN_USERS_DATA = "login_users_data"

fun getLoginUserData(): List<LoginDetail> = Gson().fromJson<List<LoginDetail>>(ShoeStoreApp.sharedPref?.getString(LOGIN_USERS_DATA, "[]") ?: "[]")

fun addLoginUserData(userData: LoginDetail){
    val data = getLoginUserData().toMutableList().apply { add(userData) }
    ShoeStoreApp.sharedPref?.edit {
        putString(LOGIN_USERS_DATA, Gson().toJson(data))
    }
}
