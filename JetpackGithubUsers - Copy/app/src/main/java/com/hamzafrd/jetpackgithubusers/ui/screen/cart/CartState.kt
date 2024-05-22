package com.hamzafrd.jetpackgithubusers.ui.screen.cart

import com.hamzafrd.jetpackgithubusers.model.HeroList

data class CartState(
    val heroList: List<HeroList>,
    val totalRequiredPoint: Int
)