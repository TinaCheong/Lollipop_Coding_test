package com.tina.lollipopcodingtest.data.source.local

import android.content.Context
import androidx.lifecycle.LiveData
import com.tina.lollipopcodingtest.data.News
import com.tina.lollipopcodingtest.data.NewsListResult
import com.tina.lollipopcodingtest.data.Result
import com.tina.lollipopcodingtest.data.source.LollipopDataSource

class LollipopLocalDataSource : LollipopDataSource {
    
    override suspend fun getNewsByNetwork(nextPage: String): Result<NewsListResult> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getNewsByDatabase(): LiveData<List<News>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun insertNews(news: News) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}