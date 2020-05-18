package com.tina.lollipopcodingtest.newslist

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tina.lollipopcodingtest.LollipopApplication
import com.tina.lollipopcodingtest.R
import com.tina.lollipopcodingtest.data.source.LollipopRepository
import com.tina.lollipopcodingtest.network.LoadApiStatus
import com.tina.lollipopcodingtest.util.Util
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class NewsListViewModel (private val lollipopRepository: LollipopRepository) : ViewModel(){

    val localData = lollipopRepository.getNewsByDatabase()

    private val _status = MutableLiveData<LoadApiStatus>()

    val status: LiveData<LoadApiStatus>
        get() = _status

    private val _error = MutableLiveData<String>()

    private var isDataLoaded: Boolean? = null

    private var viewModelJob = Job()

    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    init {
        _status.value = LoadApiStatus.DONE
        getNewsList("")
    }


    fun getNewsList(nextPage: String) {

        coroutineScope.launch {

            if (!Util.isInternetConnected()) {
                Toast.makeText(
                    LollipopApplication.instance.applicationContext,
                    Util.getString(R.string.internet_not_connected),
                    Toast.LENGTH_SHORT
                ).show()
                _status.value = LoadApiStatus.ERROR

            } else {

                _status.value = LoadApiStatus.LOADING

                when (val result = lollipopRepository.getNewsByNetwork(nextPage)) {
                    is com.tina.lollipopcodingtest.data.Result.Success -> {
                        _error.value = null
                        _status.value = LoadApiStatus.DONE
                        if (isDataLoaded == null) {
                            lollipopRepository.deleteAllNews()
                            isDataLoaded = true
                        }
                        result.data.data.list.forEach {
                            lollipopRepository.insertNews(it.data)
                        }
                    }
                    is com.tina.lollipopcodingtest.data.Result.Fail -> {
                        _error.value = result.error
                        _status.value = LoadApiStatus.ERROR
                    }
                    is com.tina.lollipopcodingtest.data.Result.Error -> {
                        _error.value = result.exception.toString()
                        _status.value = LoadApiStatus.ERROR
                    }
                    else -> {
                        _error.value = Util.getString(R.string.internet_error)
                        _status.value = LoadApiStatus.ERROR
                    }
                }
            }
        }
    }

}