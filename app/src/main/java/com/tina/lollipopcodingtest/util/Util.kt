package com.tina.lollipopcodingtest.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import com.tina.lollipopcodingtest.LollipopApplication

object Util {

    fun isInternetConnected(): Boolean {
        val cm = LollipopApplication.instance
            .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        return activeNetwork?.isConnectedOrConnecting == true
    }

    fun getString(resourceId: Int): String {
        return LollipopApplication.instance.getString(resourceId)
    }
}