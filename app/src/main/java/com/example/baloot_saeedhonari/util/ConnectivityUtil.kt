@file:Suppress("DEPRECATION")

package com.example.baloot_saeedhonari.util

import android.content.Context
import android.net.ConnectivityManager



object ConnectivityUtil {

    fun isConnected(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }
}