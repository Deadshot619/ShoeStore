package com.udacity.shoestore.utils

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

inline fun <reified T> Gson.fromJson(value: String): T =
    Gson().fromJson<T>(value, object : TypeToken<T>() {}.type)