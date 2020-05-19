package com.tina.lollipopcodingtest.data.source.local

import android.content.Context
import androidx.lifecycle.LiveData
import com.tina.lollipopcodingtest.data.News
import com.tina.lollipopcodingtest.data.NewsListResult
import com.tina.lollipopcodingtest.data.Result
import com.tina.lollipopcodingtest.data.source.LollipopDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LollipopLocalDataSource (private val context: Context) : LollipopDataSource {

    override suspend fun getNewsByNetwork(nextPage: String): Result<NewsListResult> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getNewsByDatabase(): LiveData<List<News>> {
        return NewsDatabase.getInstance(context).newsDatabaseDao.loadNewsList()
    }

    override suspend fun insertNews(news: News) {
        withContext(Dispatchers.IO) {
            NewsDatabase.getInstance(context).newsDatabaseDao.insertNews(news)
        }
    }

    override suspend fun deleteAllNews() {
        withContext(Dispatchers.IO) {
            NewsDatabase.getInstance(context).newsDatabaseDao.deleteAllNews()
        }
    }
}