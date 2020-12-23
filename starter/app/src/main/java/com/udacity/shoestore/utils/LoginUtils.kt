package com.udacity.shoestore.utils

import android.content.SharedPreferences
import androidx.core.content.edit
import com.google.gson.Gson
import com.udacity.shoestore.ShoeStoreApp
import com.udacity.shoestore.models.LoginDetail

private const val LOGIN_USERS_DATA = "login_users_data"

fun getLoginUserData(): List<LoginDetail> = Gson().fromJson(ShoeStoreApp.sharedPref?.getString(LOGIN_USERS_DATA, "[]") ?: "[]")


fun addLoginUsersData(userData: LoginDetail){
    ShoeStoreApp.sharedPref?.edit()?.putString(LOGIN_USERS_DATA, Gson().toJson(userData))?.apply()
}