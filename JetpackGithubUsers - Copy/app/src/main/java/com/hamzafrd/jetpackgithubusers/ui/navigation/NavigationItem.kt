package com.hamzafrd.jetpackgithubusers.ui.navigation

import androidx.compose.ui.graphics.vector.ImageVector
import com.hamzafrd.jetpackgithubusers.ui.navigation.Screen

data class NavigationItem(
    val title: String,
    val icon: ImageVector,
    val screen: Screen
)
