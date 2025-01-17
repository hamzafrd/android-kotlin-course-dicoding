package com.hamzafrd.jetpackgithubusers.ui.screen.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.hamzafrd.jetpackgithubusers.R
import com.hamzafrd.jetpackgithubusers.di.Injection
import com.hamzafrd.jetpackgithubusers.ui.ViewModelFactory
import com.hamzafrd.jetpackgithubusers.ui.common.UiState
import com.hamzafrd.jetpackgithubusers.ui.components.FavoritesButton
import com.hamzafrd.jetpackgithubusers.ui.theme.JetpackGithubUsersTheme

@Composable
fun DetailScreen(
    userId: Long,
    viewModel: DetailUserViewModel = viewModel(
        factory = ViewModelFactory(
            Injection.provideRepository()
        )
    ),
    navigateBack: () -> Unit,
    navigateToCart: () -> Unit,
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getUserById(userId)
            }

            is UiState.Success -> {
                val data = uiState.data
                DetailContent(
                    data.hero.image,
                    data.hero.title,
                    data.hero.description,
                    data.isFavorite,
                    onBackClick = navigateBack,
                    onAddToCart = { count ->
                        viewModel.addToFavorite(data.hero, count)
                        navigateToCart()
                    }
                )
            }

            is UiState.Error -> {}
        }
    }
}

@Composable
fun DetailContent(
    imageUrl: String,
    title: String,
    description: String,
    count: Int,
    onBackClick: () -> Unit,
    onAddToCart: (count: Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    var isFavorite by rememberSaveable { mutableIntStateOf(count) }
    Column(modifier = modifier) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .weight(1f)
        ) {
            Box {
                AsyncImage(
                    model = imageUrl,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .height(400.dp)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp))
                )

                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = stringResource(R.string.back),
                    modifier = Modifier
                        .padding(16.dp)
                        .clickable { onBackClick() }
                )
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = title,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.headlineSmall.copy(
                        fontWeight = FontWeight.ExtraBold
                    ),
                )

                Text(
                    text = description,
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Justify,
                )
            }
        }
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(4.dp)
                .background(LightGray)
        )
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            FavoritesButton(
                text = if(isFavorite < 1) stringResource(R.string.add_to_favorite) else stringResource(R.string.remove_from_favorite),
                onClick = {
                    if (isFavorite < 1) isFavorite++ else isFavorite--
                    onAddToCart(isFavorite)
                },
            )
        }
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun DetailContentPreview() {
    JetpackGithubUsersTheme {
        DetailContent(
            "https://raw.githubusercontent.com/dicodingacademy/assets/main/android_compose_academy/pahlawan/1.jpg",
            "Jaket Hoodie Dicoding",
            "ini Description",
            1,
            onBackClick = {},
            onAddToCart = {}
        )
    }
}