package com.udacity.shoestore.utils

import androidx.core.content.edit
import com.google.gson.Gson
import com.udacity.shoestore.ShoeStoreApp
import com.udacity.shoestore.models.LoginDetail

private const val LOGIN_USERS_DATA = "login_users_data"

fun getAllLoginUserData(): List<LoginDetail> = Gson().fromJson<List<LoginDetail>>(ShoeStoreApp.sharedPref?.getString(LOGIN_USERS_DATA, "[]") ?: "[]")

fun addLoginUserData(userData: LoginDetail){
    val data = getAllLoginUserData().toMutableList().apply { add(userData) }
    ShoeStoreApp.sharedPref?.edit {
        putString(LOGIN_USERS_DATA, Gson().toJson(data))
    }
}

/**
 * Method to check if user is already present in DB
 */
fun isUserAlreadyPresent(username: String): Boolean = getAllLoginUserData().find { it.username == username } != null

fun getUserLoginDetail(username: String): LoginDetail? = getAllLoginUserData().find { it.username == username }