package com.everymeal.presentation.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.everymeal.presentation.R
import com.everymeal.presentation.components.EveryMealLineButton
import com.everymeal.presentation.components.EveryMealRestaurantItem
import com.everymeal.presentation.ui.theme.EveryMeal_AndroidTheme
import com.everymeal.presentation.ui.theme.Gray100
import com.everymeal.presentation.ui.theme.Gray300
import com.everymeal.presentation.ui.theme.Gray500
import com.everymeal.presentation.ui.theme.Gray800
import com.everymeal.presentation.ui.theme.Paddings

data class Restaurant(
    val name: String,
    val category: String,
    val image: List<Int>,
    val rating: Double,
    val reviewCount: Int,
    val loveCount: Int,
)
@Composable
fun HomeScreen(

) {
    val items = listOf(
        Restaurant(
            name = "슈니",
            category = "한식",
            image = listOf(
                R.drawable.image_myongji,
                R.drawable.image_myongji,
            ),
            rating = 4.5,
            reviewCount = 100,
            loveCount = 100,
        ),
        Restaurant(
            name = "왕가주방",
            category = "중식",
            image = listOf(
                R.drawable.image_myongji,
                R.drawable.image_myongji,
                R.drawable.image_myongji,
                R.drawable.image_myongji,
            ),
            rating = 4.5,
            reviewCount = 100,
            loveCount = 100,
        ),
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White),
    ) {
        HomeTopAppBar()
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth(),
        ) {
            item {
                HomeMainTopLayout()
                HomeCategoryList()
                Spacer(modifier = Modifier.padding(10.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = Gray100)
                        .height(12.dp)
                        .padding(horizontal = 0.dp)
                )
                Spacer(modifier = Modifier.padding(16.dp))
                Text(
                    modifier = Modifier.padding(start = 20.dp),
                    text = stringResource(R.string.home_top_good_restaurant),
                    fontSize = 20.sp,
                    color = Color.Black,
                    fontWeight = Bold,
                )
                Spacer(modifier = Modifier.padding(8.dp))
            }
            items(items.size) { index ->
                val item = items[index]
                EveryMealRestaurantItem(item) {

                }
            }
            item {
                EveryMealLineButton(
                    text = stringResource(R.string.home_restaurant_button_text),
                    onClick = {

                    },
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopAppBar() {
    TopAppBar(
        modifier = Modifier.background(color = Color.White),
        title = {
            Image(
                imageVector = ImageVector.vectorResource(R.drawable.everymeal),
                contentDescription = stringResource(R.string.home_everymeal_logo_text),
            )
        },
        actions = {
            IconButton(
                onClick = {

                },
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.icon_search_mono),
                    contentDescription = stringResource(R.string.home_search_logo),
                )
            }
            IconButton(
                onClick = {

                },
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.icon_heart_mono),
                    contentDescription = stringResource(R.string.home_love_logo),
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.White,
        ),
    )
}

@Composable
fun HomeMainTopLayout() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
            .background(Gray300, RoundedCornerShape(12.dp))
            .padding(horizontal = Paddings.extra, vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        // 예시 이미지
        Image(
            imageVector = ImageVector.vectorResource(R.drawable.ic_homemenu_recommend),
            contentDescription = stringResource(R.string.home_top_category_main_image),
        )
        Spacer(modifier = Modifier.padding(10.dp))
        Column {
            Text(
                text = stringResource(id = R.string.home_top_category_title, "슈니"),
                fontSize = 15.sp,
                color = Gray800
            )
            Text(
                text = stringResource(R.string.home_top_category_sub_title),
                fontSize = 14.sp,
                color = Gray500
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.icon_arrow_right),
            contentDescription = stringResource(R.string.icon_arrow_right),
            tint = Gray500
        )
    }
}

@Composable
fun HomeCategoryList() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        CategoryItem(
            R.drawable.ic_homemenu_recommend,
            R.string.home_top_category_recommend
        ) {

        }
        CategoryItem(
            R.drawable.ic_homemenu_bap,
            R.string.home_top_category_rice
        ) {

        }
        CategoryItem(
            R.drawable.ic_homemenu_cake,
            R.string.home_top_category_cafe
        ) {

        }
        CategoryItem(
            R.drawable.ic_homemenu_beer,
            R.string.home_top_category_drink
        ) {

        }
    }
}

@Composable
fun CategoryItem(
    categoryIcon: Int,
    categoryText: Int,
    onClick: () -> Unit,
) {
    Surface(
        shape = RoundedCornerShape(12.dp),
    ) {
        Column(
            modifier = Modifier
                .clickable { onClick() }
                .background(Color.White, RoundedCornerShape(12.dp))
                .padding(horizontal = 17.dp, vertical = 4.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                imageVector = ImageVector.vectorResource(categoryIcon),
                contentDescription = stringResource(R.string.home_top_category_list),
            )
            Text(
                text = stringResource(categoryText),
                fontSize = 12.sp,
                color = Color.Black
            )
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    EveryMeal_AndroidTheme {
        HomeScreen()
    }
}