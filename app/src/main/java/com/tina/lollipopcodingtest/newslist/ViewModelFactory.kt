package com.tina.lollipopcodingtest.newslist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tina.lollipopcodingtest.data.source.LollipopRepository

@Suppress("UNCHECKED_CAST")
class ViewModelFactory constructor(
    private val lollipopRepository: LollipopRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>) =
        with(modelClass) {
            when {
                isAssignableFrom(NewsListViewModel::class.java) ->
                    NewsListViewModel(lollipopRepository)

                else ->
                    throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
            }
        } as T
}