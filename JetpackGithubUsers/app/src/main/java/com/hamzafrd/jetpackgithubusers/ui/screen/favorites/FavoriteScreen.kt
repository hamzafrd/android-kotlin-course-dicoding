package com.hamzafrd.jetpackgithubusers.ui.screen.favorites

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.hamzafrd.jetpackgithubusers.R
import com.hamzafrd.jetpackgithubusers.di.Injection
import com.hamzafrd.jetpackgithubusers.ui.ViewModelFactory
import com.hamzafrd.jetpackgithubusers.ui.common.UiState
import com.hamzafrd.jetpackgithubusers.ui.components.CartItem

@Composable
fun CartScreen(
    viewModel: FavoritesViewModel = viewModel(
        factory = ViewModelFactory(
            Injection.provideRepository()
        )
    ),
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getFavoriteHero()
            }

            is UiState.Success -> {
                CartContent(
                    uiState.data,
                    onHeroCountChanged = { userId, count ->
                        viewModel.updateFavoriteHero(userId, count)
                    },
                )
            }

            is UiState.Error -> {}
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartContent(
    state: FavoriteState,
    onHeroCountChanged: (id: Long, count: Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        CenterAlignedTopAppBar(
            title = {
                Text(
                    text = stringResource(R.string.menu_favorite),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp),
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center
                )
            }
        )
        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.weight(weight = 1f)
        ) {
            items(state.heroList, key = { it.hero.id }) { item ->
                CartItem(
                    userId = item.hero.id,
                    imageUrl = item.hero.image,
                    title = item.hero.title,
                    isFavorite = item.isFavorite,
                    onHeroCountChanged = onHeroCountChanged,
                )
                Divider()
            }
        }
    }
}