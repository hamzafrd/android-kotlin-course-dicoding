package com.example.githubusers.data

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import com.example.githubusers.data.api.ApiService
import com.example.githubusers.data.local.entity.UserEntity
import com.example.githubusers.data.local.room.UserDao
import com.example.githubusers.data.local.room.UserDatabase
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class UserRepository(application: Application) {
    private var mUserDao: UserDao
    private var executorService: ExecutorService = Executors.newSingleThreadExecutor()

    init {
        val db = UserDatabase.getRoomDatabase(application)
        mUserDao = db.userDao()
    }

    fun getAllFavoriteUser(): LiveData<List<UserEntity>> {
        return mUserDao.getBookmarkedUsers()
    }

    fun insert(user: UserEntity) {
        executorService.execute {
            mUserDao.insert(user)
        }
    }

    fun delete(user: UserEntity) {
        executorService.execute {
            mUserDao.delete(user)
        }
    }
}