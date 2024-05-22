package com.example.githubusers.data.local.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.githubusers.data.local.entity.UserEntity

@Dao
interface UserDao {
    @Query("SELECT * FROM github_user ORDER BY id DESC")
    fun getUsers(): LiveData<List<UserEntity>>

    @Query("SELECT * FROM github_user")
    fun getBookmarkedUsers(): LiveData<List<UserEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(user: UserEntity)

    @Update
    fun update(user: UserEntity)

    @Delete
    fun delete(user: UserEntity)
}