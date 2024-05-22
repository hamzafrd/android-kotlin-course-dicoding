package com.hamzafrd.jetpackgithubusers.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.hamzafrd.jetpackgithubusers.R
import com.hamzafrd.jetpackgithubusers.ui.theme.JetpackGithubUsersTheme
import com.hamzafrd.jetpackgithubusers.ui.theme.RoundedShape

@Composable
fun UserItem(
    imageUrl: String,
    title: String,
    userId: Int,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
    ) {
        AsyncImage(
            model = imageUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(170.dp)
                .clip(RoundedShape.medium)
        )

        Text(
            text = title,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.ExtraBold
            )
        )
        Text(
            text = stringResource(R.string.user_id, userId),
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.secondary
        )
    }
}

@Composable
@Preview(showBackground = true)
fun RewardItemPreview() {
    JetpackGithubUsersTheme {
        UserItem(
            "https://raw.githubusercontent.com/dicodingacademy/assets/main/android_compose_academy/pahlawan/1.jpg",
            "Jaket Hoodie Dicoding",
            4000
        )
    }
}