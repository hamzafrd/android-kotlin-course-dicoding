package com.hamzafrd.jetpackgithubusers.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
fun CartItem(
    userId: Long,
    imageUrl: String,
    title: String,
    count: Int,
    onProductCountChanged: (id: Long, count: Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.fillMaxWidth()
    ) {
        AsyncImage(
            model = imageUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(8.dp)
                .size(90.dp)
                .clip(RoundedShape.small)
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .weight(1.0f)
        ) {
            Text(
                text = title,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.ExtraBold
                )
            )
            Text(
                text = stringResource(
                    R.string.user_id,
                    userId
                ),
                color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.titleSmall,
            )
        }
        ProductCounter(
            orderId = userId,
            orderCount = count,
            onProductIncreased = { onProductCountChanged(userId, count + 1) },
            onProductDecreased = { onProductCountChanged(userId, count - 1) },
            modifier = Modifier.padding(8.dp)
        )
    }
}

@Composable
@Preview(showBackground = true)
fun CartItemPreview() {
    JetpackGithubUsersTheme {
        CartItem(
            4,
            "https://raw.githubusercontent.com/dicodingacademy/assets/main/android_compose_academy/pahlawan/1.jpg",
            "Jaket Hoodie Dicoding",
            0,
            onProductCountChanged = { _, _ -> },
        )
    }
}