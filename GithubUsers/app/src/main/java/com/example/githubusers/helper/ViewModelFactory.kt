package com.example.githubusers.helper

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.githubusers.ui.favorite.FavoriteViewModel
import com.example.githubusers.ui.settings.SettingViewModel
import com.example.githubusers.ui.detail.DetailViewModel
import com.example.githubusers.ui.home.HomeViewModel

class ViewModelFactory private constructor(
    private val mApplication: Application? = null,
    private val mPref: SettingPreference? = null
) : ViewModelProvider.NewInstanceFactory() {


    companion object {
        @Volatile
        private var INSTANCE: ViewModelFactory? = null

        @JvmStatic
        fun getInstance(
            application: Application? = null,
            pref: SettingPreference? = null
        ): ViewModelFactory {
            synchronized(ViewModelFactory::class.java) {
                INSTANCE = ViewModelFactory(application, pref)
            }

            return INSTANCE as ViewModelFactory
        }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(application = mApplication!!) as T
        } else if (modelClass.isAssignableFrom(FavoriteViewModel::class.java)) {
            return FavoriteViewModel(application = mApplication!!) as T
        } else if (modelClass.isAssignableFrom(SettingViewModel::class.java)) {
            return SettingViewModel(pref = mPref!!) as T
        } else if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(pref = mPref!!) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class ${modelClass.name}")
    }
}