package com.example.githubusers.ui.favorite

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.githubusers.data.UserRepository
import com.example.githubusers.data.local.entity.UserEntity

class FavoriteViewModel(application: Application) : ViewModel() {
    private val mUserRepository = UserRepository(application)

    fun getAllFavoriteUser(): LiveData<List<UserEntity>> {
        return mUserRepository.getAllFavoriteUser()
    }
}
