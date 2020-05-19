package com.tina.lollipopcodingtest.data.source.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.tina.lollipopcodingtest.data.News
import retrofit2.http.DELETE

@Dao
interface NewsDatabaseDao {

    @Insert
    fun insertNews(news: News)

    @Query("SELECT*FROM news_list_table ORDER BY autoId ASC")
    fun loadNewsList() : LiveData<List<News>>

    @Query("DELETE FROM news_list_table")
    fun deleteAllNews()

}