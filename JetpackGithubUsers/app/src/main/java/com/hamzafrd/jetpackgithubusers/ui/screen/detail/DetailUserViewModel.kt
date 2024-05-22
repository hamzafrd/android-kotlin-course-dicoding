package com.hamzafrd.jetpackgithubusers.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hamzafrd.jetpackgithubusers.data.UserRepository
import com.hamzafrd.jetpackgithubusers.model.HeroList
import com.hamzafrd.jetpackgithubusers.model.Hero
import com.hamzafrd.jetpackgithubusers.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailUserViewModel(
    private val repository: UserRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<HeroList>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<HeroList>>
        get() = _uiState

    fun getUserById(rewardId: Long) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            _uiState.value = UiState.Success(repository.getHeroById(rewardId))
        }
    }

    fun addToFavorite(hero: Hero, count: Int) {
        viewModelScope.launch {
            repository.updateFavoriteHero(hero.id, count)
        }
    }
}