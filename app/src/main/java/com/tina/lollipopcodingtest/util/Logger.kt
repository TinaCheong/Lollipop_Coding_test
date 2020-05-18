package com.tina.lollipopcodingtest.util

import android.util.Log
import com.tina.lollipopcodingtest.BuildConfig

object Logger {

    private const val TAG = "Tina_Test"

    fun v(content: String) { if (BuildConfig.LOGGER_VISIABLE) Log.v(TAG, content) }
}