package com.hamzafrd.newsrepository.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.hamzafrd.newsrepository.BuildConfig
import com.hamzafrd.newsrepository.data.local.entity.NewsEntity
import com.hamzafrd.newsrepository.data.local.room.NewsDao
import com.hamzafrd.newsrepository.data.remote.response.NewsResponse
import com.hamzafrd.newsrepository.data.remote.retrofit.ApiService
import com.hamzafrd.newsrepository.utils.AppExecutors
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

//menyimpan data dari network ke local
class NewsRepository private constructor(
    private val api: ApiService,
    private val newsDao: NewsDao,
    private val appExecutors: AppExecutors
) {
    private val result = MediatorLiveData<Result<List<NewsEntity>>>()

    fun getHeadlineNews(): LiveData<Result<List<NewsEntity>>> {
        //inisiasi
        result.value = Result.Loading

        //mengambil network
        val client = api.getNews(BuildConfig.API_KEY)
        client.enqueue(object : Callback<NewsResponse> {
            override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                if (response.isSuccessful) {
                    val articles = response.body()?.articles

                    val newsList = ArrayList<NewsEntity>()
                    appExecutors.diskIO.execute {
                        articles?.forEach { article ->
                            //membaca apakah sudah di bookmark ?
                            val isBookmarked = newsDao.isNewsBookmarked(article.title)
                            val news = NewsEntity(
                                article.title,
                                article.publishedAt,
                                article.urlToImage,
                                article.url,
                                isBookmarked
                            )
                            //mengubah data menjadi entity
                            newsList.add(news)
                        }
                        //Menghapus semua data dari database yang tidak ditandai bookmark.
                        newsDao.deleteAll()

                        //Memasukkan data baru dari internet ke dalam database
                        newsDao.insertNews(newsList)
                    }
                }
            }

            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                result.value = Result.Error(t.message.toString())
            }
        })

        //mengambil data dari database yang merupakan livedata
        val localData = newsDao.getNews()
        result.addSource(localData) { newData: List<NewsEntity> ->
            result.value = Result.Success(newData)
        }
        return result
    }

    fun getBookmarkedNews(): LiveData<List<NewsEntity>> {
        return newsDao.getBookmarkedNews()
    }
    fun setBookmarkedNews(news: NewsEntity, bookmarkState: Boolean) {
        appExecutors.diskIO.execute {
            news.isBookmarked = bookmarkState
            newsDao.updateNews(news)
        }
    }

    companion object {
        @Volatile
        private var instance: NewsRepository? = null

        fun getInstance(
            apiService: ApiService,
            newsDao: NewsDao,
            appExecutors: AppExecutors
        ): NewsRepository =
            instance ?: synchronized(this) {
                instance ?: NewsRepository(apiService, newsDao, appExecutors)
            }.also { instance = it }
    }
}
