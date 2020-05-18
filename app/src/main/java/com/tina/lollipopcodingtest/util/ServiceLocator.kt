package com.tina.lollipopcodingtest.util

import android.content.Context
import androidx.annotation.VisibleForTesting
import com.tina.lollipopcodingtest.data.source.DefaultLollipopRepository
import com.tina.lollipopcodingtest.data.source.LollipopDataSource
import com.tina.lollipopcodingtest.data.source.LollipopRepository
import com.tina.lollipopcodingtest.data.source.local.LollipopLocalDataSource
import com.tina.lollipopcodingtest.data.source.remote.LollipopRemoteDataSource

object ServiceLocator {

    @Volatile
    var lollipopRepository: LollipopRepository? = null
        @VisibleForTesting set

    fun provideNewsRepository(context: Context): LollipopRepository {
        synchronized(this) {
            return lollipopRepository
                ?: lollipopRepository
                ?: createLollipopRepository(context)
        }
    }

    private fun createLollipopRepository(context: Context): LollipopRepository {
        return DefaultLollipopRepository(
            LollipopRemoteDataSource,
            createLocalDataSource(context)
        )
    }

    private fun createLocalDataSource(context: Context): LollipopDataSource {
        return LollipopLocalDataSource(context)
    }
}