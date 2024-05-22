package com.hamzafrd.newsrepository.di

import android.content.Context
import com.hamzafrd.newsrepository.data.NewsRepository
import com.hamzafrd.newsrepository.data.SettingPreferences
import com.hamzafrd.newsrepository.data.local.room.NewsDatabase
import com.hamzafrd.newsrepository.data.remote.retrofit.ApiConfig
import com.hamzafrd.newsrepository.utils.AppExecutors

object Injection {
    fun provideRepository(context: Context): NewsRepository {
        val apiService = ApiConfig.getApiService()
        val database = NewsDatabase.getInstance(context)
        val dao = database.newsDao()
        val appExecutors = AppExecutors()
        return NewsRepository.getInstance(apiService, dao, appExecutors)
    }
}