# Lollipop_Coding_test
主要需求︰用分頁讀取的形式實作無限列表，讀取時同時寫入資料庫中，但再次讀取時需要跟據 api 的資料同時更新。

### 1.設定 Retrofit 讀取資料的方式
在 @GET 方法中用 limt 和 after 來設定每次拿取的資料數量和該讀取哪一筆資料後的資料（這邊設定每次是拿 4 筆）

```sh
private const val DEFAULT_LIMIT = "3"
private const val END_POINT = "r/Taiwan/hot.json?"

@GET(END_POINT)
    fun getNewsList(@Query("limit") limit: String = DEFAULT_LIMIT, @Query("after") after: String? = null): Deferred<NewsListResult>
```

### 2. 建立 SharedPreference 來判斷此次讀取資料的方法是否為第一次
```sh
object DataManager {

    private const val SETTING_DATA = "setting_data"
    private const val DATA_LOADED = "data_loaded"

    var isLoaded: Boolean
        get() {
            val sharedPreferences =
                LollipopApplication.instance.getSharedPreferences(SETTING_DATA, Context.MODE_PRIVATE)
            return sharedPreferences.getBoolean(DATA_LOADED, true)
        }
        set(value) {
            val setting = LollipopApplication.instance.getSharedPreferences(SETTING_DATA, 0)
            setting.edit()
                .putBoolean(DATA_LOADED, value)
                .apply()
        }
}
```
### 3. 在呼叫 Api 的方法裏加入判斷條件
如果為第一次讀取，則需要先清除取本地資料庫的資料，然後再寫入新的資料，然後在 Fragment onDestory 時改回預設值
```sh
fun getNewsList(nextPage: String) {
        
          ...
          
            when (val result = lollipopRepository.getNewsByNetwork(nextPage)) {
                    is com.tina.lollipopcodingtest.data.Result.Success -> {
                        _error.value = null
                        _status.value = LoadApiStatus.DONE
                        if (DataManager.isLoaded) {
                            lollipopRepository.deleteAllNews()
                            DataManager.isLoaded = false
                        }
                        result.data.data.list.forEach {
                            lollipopRepository.insertNews(it.data)
                        }
                    }
                    
                    ...
                    
                }
```

```sh
override fun onDestroy() {
        super.onDestroy()
        DataManager.isLoaded = true
    }
```

### 4. 分頁讀取實作
在 Adapter 的 onBindViewHolder 裏，用 itemCount 和 position 去判斷目前的 item 是顯示到哪一個位置，如果它們之間只剩下 1 ，那就要拿最後那筆資料的 name 去做為 call api 的 after (nextPage)，並讀取後 4 筆的資料
```sh
override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        
        ...
        
        if (itemCount - position == 1 ) {
            viewModel.getNewsList(item.name)
        }
    }
```
