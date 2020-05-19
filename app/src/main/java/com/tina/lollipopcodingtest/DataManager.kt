package com.tina.lollipopcodingtest

import android.content.Context

object DataManager {

    private const val SETTING_DATA = "setting_data"
    private const val DATA_LOADED = "data_loaded"

    var isLoaded: Boolean
        get() {
            val sharedPreferences =
                LollipopApplication.instance.getSharedPreferences(SETTING_DATA, Context.MODE_PRIVATE)
            return sharedPreferences.getBoolean(DATA_LOADED, true)
        }
        set(value) {
            val setting = LollipopApplication.instance.getSharedPreferences(SETTING_DATA, 0)
            setting.edit()
                .putBoolean(DATA_LOADED, value)
                .apply()
        }
}