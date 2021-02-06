package com.udacity.shoestore.utils

import androidx.core.content.edit
import com.google.gson.Gson
import com.udacity.shoestore.ShoeStoreApp
import com.udacity.shoestore.models.LoginDetail
import java.lang.Exception

private const val LOGIN_USERS_DATA = "login_users_data"
private const val USER_LOGGED_IN_DATA = "user_logged_in_data"
private const val INSTRUCTION_COMPLETED_DATA = "instruction_completed_data"

//Variable to hold User's Login Status
var userLoggedInData: LoginDetail?
    get() = try {
        Gson().fromJson(ShoeStoreApp.sharedPref?.getString(USER_LOGGED_IN_DATA, "") ?: "")
    } catch (e: Exception){ null }
    set(value) = ShoeStoreApp.sharedPref!!.edit { putString(USER_LOGGED_IN_DATA, Gson().toJson(value)) }

//Variable to indicate whether Welcome & Instruction Screens have been gone through.
var isInstructionCompleted: Boolean
    get() = ShoeStoreApp.sharedPref?.getBoolean(INSTRUCTION_COMPLETED_DATA, false) ?: false
    set(value) = ShoeStoreApp.sharedPref!!.edit { putBoolean(INSTRUCTION_COMPLETED_DATA, value) }

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