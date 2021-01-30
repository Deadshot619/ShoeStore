package com.udacity.shoestore

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import timber.log.Timber

class ShoeStoreApp: Application() {
    companion object{
        var sharedPref: SharedPreferences? = null
            private set
    }

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())

        sharedPref = getSharedPreferences(this.packageName, Context.MODE_PRIVATE)
    }
}