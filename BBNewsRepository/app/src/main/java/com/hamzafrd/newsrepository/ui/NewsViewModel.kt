package com.hamzafrd.newsrepository.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.hamzafrd.newsrepository.data.NewsRepository
import com.hamzafrd.newsrepository.data.SettingPreferences
import com.hamzafrd.newsrepository.data.local.entity.NewsEntity
import kotlinx.coroutines.launch

class NewsViewModel(
    private val newsRepository: NewsRepository,
    private val pref: SettingPreferences
) : ViewModel() {
    fun getHeadlineNews() = newsRepository.getHeadlineNews()

    fun getBookmarkedNews() = newsRepository.getBookmarkedNews()

    fun bookmarkNews(news: NewsEntity) {
        newsRepository.setBookmarkedNews(news, true)
    }

    fun unBookmarkNews(news: NewsEntity) {
        newsRepository.setBookmarkedNews(news, false)
    }

    fun getThemeSettings(): LiveData<Boolean> {
        return pref.getThemeSetting().asLiveData()
    }

    fun saveThemeSetting(isDarkModeActive: Boolean) {
        viewModelScope.launch {
            pref.saveThemeSetting(isDarkModeActive)
        }
    }
}
