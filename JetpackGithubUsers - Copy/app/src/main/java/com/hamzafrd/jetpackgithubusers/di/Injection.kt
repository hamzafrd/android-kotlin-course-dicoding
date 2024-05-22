package com.hamzafrd.jetpackgithubusers.di

import com.hamzafrd.jetpackgithubusers.data.UserRepository

object Injection {
    fun provideRepository(): UserRepository {
        return UserRepository.getInstance()
    }
}