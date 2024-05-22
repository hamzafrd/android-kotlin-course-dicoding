package com.hamzafrd.jetpackgithubusers.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hamzafrd.jetpackgithubusers.ui.screen.favorites.FavoritesViewModel
import com.hamzafrd.jetpackgithubusers.ui.screen.detail.DetailUserViewModel
import com.hamzafrd.jetpackgithubusers.ui.screen.home.HomeViewModel
import com.hamzafrd.jetpackgithubusers.data.UserRepository

class ViewModelFactory(private val repository: UserRepository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(DetailUserViewModel::class.java)) {
            return DetailUserViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(FavoritesViewModel::class.java)) {
            return FavoritesViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}