package com.tina.lollipopcodingtest.data.source

import androidx.lifecycle.LiveData
import com.tina.lollipopcodingtest.data.News
import com.tina.lollipopcodingtest.data.NewsListResult
import com.tina.lollipopcodingtest.data.Result

class DefaultLollipopRepository (private val lollipopRemoteDataSource: LollipopDataSource,
                                 private val lollipopLocalDataSource: LollipopDataSource) : LollipopRepository {

    override suspend fun getNewsByNetwork(nextPage: String): Result<NewsListResult> {
        return lollipopRemoteDataSource.getNewsByNetwork(nextPage)
    }

    override fun getNewsByDatabase(): LiveData<List<News>> {
        return lollipopLocalDataSource.getNewsByDatabase()
    }

    override suspend fun insertNews(news: News) {
        return lollipopLocalDataSource.insertNews(news)
    }

    override suspend fun deleteAllNews() {
        return lollipopLocalDataSource.deleteAllNews()
    }

}