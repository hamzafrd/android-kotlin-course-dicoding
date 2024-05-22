package com.hamzafrd.jcompose

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.ExpandMore
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hamzafrd.jcompose.data.local.model.BottomBarItem
import com.hamzafrd.jcompose.data.local.model.Category
import com.hamzafrd.jcompose.data.local.model.Menu
import com.hamzafrd.jcompose.data.local.model.dummyBestSellerMenu
import com.hamzafrd.jcompose.data.local.model.dummyCategory
import com.hamzafrd.jcompose.data.local.model.dummyMenu
import com.hamzafrd.jcompose.ui.components.HomeSection
import com.hamzafrd.jcompose.ui.components.MenuItem
import com.hamzafrd.jcompose.ui.components.Search
import com.hamzafrd.jcompose.ui.theme.JComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JComposeTheme {
                JetCoffeeApp()
            }
        }
    }

    @Composable
    fun JetCoffeeApp(modifier: Modifier = Modifier) {
//        Surface(
//            modifier = modifier.fillMaxSize(),
//            color = MaterialTheme.colorScheme.background
//        ) {
        Scaffold(
            bottomBar = { BottomBar() },
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .padding(innerPadding)
            ) {
                Banner()
                HomeSection(
                    title = stringResource(R.string.section_category),
                    content = { CategoryRow() }
                )
                HomeSection(
                    title = stringResource(R.string.menu_favorite),
                    content = { MenuRow(dummyMenu) }
                )
                HomeSection(
                    title = stringResource(R.string.section_best_seller_menu),
                    content = { MenuRow(dummyBestSellerMenu) }
                )
            }
        }
        //        }
    }

    @Preview(showBackground = true, group = "Canvas Only")
    @Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES, group = "Canvas Only")
    @Preview(
        name = "Device Preview",
        group = "Device",
        device = Devices.PIXEL_4,
        showSystemUi = true,
        uiMode = UI_MODE_NIGHT_YES
    )
    @Composable
    fun JetCoffeeAppPreview() {
        JComposeTheme {
            JetCoffeeApp()
        }
    }

    @Composable
    fun Banner(
        modifier: Modifier = Modifier,
    ) {
        Box(modifier = modifier) {
            Image(
                painter = painterResource(R.drawable.banner),
                contentDescription = "Banner Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier.height(160.dp)
            )
            Search()
        }
    }

    @Composable
    fun CategoryRow(
        modifier: Modifier = Modifier,
    ) {
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(horizontal = 16.dp),
            modifier = modifier
        ) {
            items(dummyCategory, key = { it.textCategory }) { category ->
                CategoryItem(category)
            }
        }
    }

    @Composable
    @Preview(showBackground = true, group = "Category List")
    fun CategoryRowPreview() {
        JComposeTheme {
            CategoryRow()
        }
    }

    @Composable
    fun CategoryItem(
        category: Category,
        modifier: Modifier = Modifier,
    ) {
        Column(
            modifier = modifier,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(category.imageCategory),
                contentDescription = null,
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape)
            )
            Text(
                text = stringResource(category.textCategory),
                fontSize = 10.sp,
                modifier = Modifier.paddingFromBaseline(top = 16.dp, bottom = 8.dp)
            )
        }
    }

    @Composable
    @Preview(showBackground = true, group = "categoryItem")
    fun CategoryItemPreview() {
        JComposeTheme {
            CategoryItem(
                category = Category(
                    R.drawable.icon_category_cappuccino,
                    R.string.category_cappuccino,
                ),
                modifier = Modifier.padding(horizontal = 8.dp)
            )
        }
    }

    @Composable
    fun MenuRow(
        listMenu: List<Menu>,
        modifier: Modifier = Modifier,
    ) {
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(horizontal = 16.dp),
            modifier = modifier
        ) {
            items(listMenu, key = { it.title }) { menu ->
                MenuItem(menu)
            }
        }
    }

    @Composable
    @Preview(showBackground = true, group = "Menu List")
    fun PreviewMenuRow() {
        JComposeTheme {
            Column {
                MenuRow(dummyMenu)
                MenuRow(dummyBestSellerMenu)
            }
        }
    }

    @Composable
    fun BottomBar(
        modifier: Modifier = Modifier,
    ) {
        NavigationBar(
            modifier = modifier,
            containerColor = MaterialTheme.colorScheme.background,
        ) {
            val navigationItems = listOf(
                BottomBarItem(
                    title = stringResource(R.string.menu_home),
                    icon = Icons.Default.Home
                ),
                BottomBarItem(
                    title = stringResource(R.string.menu_favorite),
                    icon = Icons.Default.Favorite
                ),
                BottomBarItem(
                    title = stringResource(R.string.menu_profile),
                    icon = Icons.Default.AccountCircle
                ),
            )
            navigationItems.map {
                NavigationBarItem(
                    icon = {
                        Icon(
                            imageVector = it.icon,
                            contentDescription = it.title
                        )
                    },
                    label = {
                        Text(it.title)
                    },
                    selected = it.title == navigationItems[0].title,
                    onClick = {}
                )
            }
        }
    }

    @Composable
    @Preview(showBackground = true, group = "BottomAppBar")
    fun BottomBarPreview() {
        JComposeTheme {
            BottomBar()
        }
    }

    @Composable
    private fun GreetingList(names: List<String>) {
        if (names.isNotEmpty()) {
            LazyColumn {
                this.items(names) {
                    Greeting(it)
                }
            }
        } else {
            Box(contentAlignment = Alignment.Center) {
                Text("No people to greet :(")
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    //dengan cara import
    var isExpanded by remember { mutableStateOf(false) }
    val animatedSizeDp by animateDpAsState(
        targetValue = if (isExpanded) 120.dp else 80.dp,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        )
    )
    Card(
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        )
    ) {
        Row(
            modifier = modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Image(
                painter = painterResource(id = R.drawable.jetpack_compose),
                contentDescription = "Logo Jetpack Compose",
                modifier = modifier.size(animatedSizeDp)
            )

            Spacer(modifier = modifier.width(20.dp))
            Column(modifier = modifier.weight(1f)) {
                Text(
                    text = "Hello $name!",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Selamat Datang di JCompose.",
//                    fontStyle = FontStyle.Italic,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontStyle = FontStyle.Italic
                    )
                )
            }

            IconButton(onClick = { isExpanded = !isExpanded }) {
                Icon(
                    imageVector = if (isExpanded) Icons.Filled.ExpandLess else Icons.Outlined.ExpandMore,
                    contentDescription = if (isExpanded) "Show less" else "Show more"
                )
            }

        }
    }
}

@Preview(showBackground = true, group = "listItem")
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES, group = "listItem")
@Composable
fun DefaultPreview() {
    JComposeTheme {
        Greeting("Jetpack Compose")
    }
}