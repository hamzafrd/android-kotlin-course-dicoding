package com.hamzafrd.jetpackgithubusers.ui.screen.favorites

import com.hamzafrd.jetpackgithubusers.model.HeroList

data class FavoriteState(
    val heroList: List<HeroList>,
    val totalRequiredPoint: Int
)