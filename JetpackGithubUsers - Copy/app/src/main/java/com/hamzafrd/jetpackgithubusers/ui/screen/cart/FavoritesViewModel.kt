package com.hamzafrd.jetpackgithubusers.ui.screen.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hamzafrd.jetpackgithubusers.data.UserRepository
import com.hamzafrd.jetpackgithubusers.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FavoritesViewModel(
    private val repository: UserRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<CartState>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<CartState>>
        get() = _uiState

    fun getAddedOrderRewards() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            repository.getFavoriteHero()
                .collect { orderReward ->
                    val totalRequiredPoint =
                        orderReward.sumOf { it.isFavorite }
                    _uiState.value = UiState.Success(CartState(orderReward, totalRequiredPoint))
                }
        }
    }

    fun updateOrderReward(rewardId: Long, count: Int) {
        viewModelScope.launch {
            repository.updateFavoriteHero(rewardId, count)
                .collect { isUpdated ->
                    if (isUpdated) {
                        getAddedOrderRewards()
                    }
                }
        }
    }
}