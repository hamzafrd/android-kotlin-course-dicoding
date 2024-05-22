package com.hamzafrd.jetpackgithubusers.ui.screen.home

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.hamzafrd.jetpackgithubusers.ScrollToTopButton
import com.hamzafrd.jetpackgithubusers.di.Injection
import com.hamzafrd.jetpackgithubusers.model.HeroList
import com.hamzafrd.jetpackgithubusers.ui.ViewModelFactory
import com.hamzafrd.jetpackgithubusers.ui.common.UiState
import com.hamzafrd.jetpackgithubusers.ui.components.UserItem
import com.hamzafrd.jetpackgithubusers.ui.components.Search
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    ),
    navigateToDetail: (Long) -> Unit,
) {
//    Box(
//        modifier = modifier.fillMaxSize(),
//        contentAlignment = Alignment.Center,
//    ) {
//        Text(stringResource(R.string.menu_home))
//    }
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getAllHero()
            }

            is UiState.Success -> {
                val query by viewModel.query
                HomeContent(
                    users = uiState.data,
                    modifier = modifier,
                    navigateToDetail = navigateToDetail,
                    viewModel = viewModel,
                )
            }

            is UiState.Error -> {
                Log.d("testing", "error")
            }
        }
    }
}

@Composable
fun HomeContent(
    users: List<HeroList>,
    modifier: Modifier = Modifier,
    navigateToDetail: (Long) -> Unit,
    viewModel: HomeViewModel,
) {
    Box(modifier = modifier) {
        val scope = rememberCoroutineScope()
        val listState = rememberLazyGridState()
        val showButton: Boolean by remember {
            derivedStateOf { listState.firstVisibleItemIndex > 0 }
        }
        LazyVerticalGrid(
            state = listState,
            columns = GridCells.Adaptive(160.dp),
            contentPadding = PaddingValues(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = modifier
        ) {
            item(span = { GridItemSpan(maxCurrentLineSpan) }) {
                val query by viewModel.query
                Search(query = query, search = viewModel::search)
            }
            items(users) { data ->
                UserItem(
                    imageUrl = data.hero.image,
                    title = data.hero.title,
                    userId = data.hero.id.toInt(),
                    modifier = Modifier.clickable {
                        navigateToDetail(data.hero.id)
                    }
                )
            }
        }

        AnimatedVisibility(
            visible = showButton,
            enter = fadeIn() + slideInVertically(),
            exit = fadeOut() + slideOutVertically(),
            modifier = Modifier
                .padding(bottom = 30.dp)
                .align(Alignment.BottomCenter)
        ) {
            ScrollToTopButton(
                onClick = {
                    scope.launch {
                        listState.scrollToItem(index = 0)
                    }
                }
            )
        }
    }
}
