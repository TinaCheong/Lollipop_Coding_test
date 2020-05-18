package com.tina.lollipopcodingtest.network

import com.bumptech.glide.gifdecoder.BuildConfig
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val HOST_NAME = "www.reddit.com"
private const val BASE_URL = "https://$HOST_NAME/"
private const val DEFAULT_LIMIT = "3"
private const val END_POINT = "r/Taiwan/hot.json?"


private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()


private val client = OkHttpClient.Builder()
    .addInterceptor(HttpLoggingInterceptor().apply {
        level = when (com.tina.lollipopcodingtest.BuildConfig.LOGGER_VISIABLE) {
            true -> HttpLoggingInterceptor.Level.BODY
            false -> HttpLoggingInterceptor.Level.NONE
        }
    })
    .build()


private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .client(client)
    .build()


interface LollipopApiService {

    @GET(END_POINT)
    fun getNewsList(@Query("limit") limit: String = DEFAULT_LIMIT, @Query("after") after: String? = null)

}

object LollipopApi {
    val retrofitService: LollipopApiService by lazy { retrofit.create(LollipopApiService::class.java) }
}