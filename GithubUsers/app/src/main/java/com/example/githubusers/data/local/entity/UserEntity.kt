package com.example.githubusers.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "github_user")
class UserEntity(
    @PrimaryKey(autoGenerate = false)
    @field:ColumnInfo(name = "username")
    val username: String,

    @field:ColumnInfo(name = "urlToImage")
    val urlToImage: String? = null,

    @field:ColumnInfo(name = "id")
    val id: Int? = null,
)