package com.tina.lollipopcodingtest.data.source

import androidx.lifecycle.LiveData
import com.tina.lollipopcodingtest.data.News
import com.tina.lollipopcodingtest.data.NewsListResult
import com.tina.lollipopcodingtest.data.Result

interface LollipopRepository {

    suspend fun getNewsByNetwork(nextPage: String): Result<NewsListResult>

    fun getNewsByDatabase(): LiveData<List<News>>

    suspend fun insertNews(news: News)

    suspend fun deleteAllNews()

}