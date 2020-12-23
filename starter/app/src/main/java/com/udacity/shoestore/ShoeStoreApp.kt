package com.udacity.shoestore

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

class ShoeStoreApp: Application() {
    companion object{
        var sharedPref: SharedPreferences? = null
            private set
    }

    override fun onCreate() {
        super.onCreate()

        sharedPref = getSharedPreferences(this.packageName, Context.MODE_PRIVATE)
    }
}