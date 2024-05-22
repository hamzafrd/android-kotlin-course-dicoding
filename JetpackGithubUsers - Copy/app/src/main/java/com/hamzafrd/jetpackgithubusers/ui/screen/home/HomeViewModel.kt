package com.hamzafrd.jetpackgithubusers.ui.screen.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hamzafrd.jetpackgithubusers.data.UserRepository
import com.hamzafrd.jetpackgithubusers.model.HeroList
import com.hamzafrd.jetpackgithubusers.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: UserRepository,
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<List<HeroList>>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<HeroList>>>
        get() = _uiState

    fun getAllRewards() {
        viewModelScope.launch {
            repository.getAllHero()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { orderRewards ->
                    _uiState.value = UiState.Success(orderRewards)
                }
        }
    }

    private val _query = mutableStateOf("")
    val query: State<String> get() = _query

    fun search(newQuery: String) {
        _query.value = newQuery
        viewModelScope.launch {
            repository.searchHero(_query.value)
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { orderRewards ->
                    _uiState.value = UiState.Success(orderRewards)
                }
        }
    }
}