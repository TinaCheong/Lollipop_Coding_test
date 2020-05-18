package com.tina.lollipopcodingtest

import android.app.Application
import com.tina.lollipopcodingtest.data.source.LollipopRepository
import com.tina.lollipopcodingtest.util.ServiceLocator
import kotlin.properties.Delegates

class LollipopApplication : Application(){

    val lollipopRepository: LollipopRepository
        get() = ServiceLocator.provideNewsRepository(this)

    companion object {
        var instance: LollipopApplication by Delegates.notNull()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

}