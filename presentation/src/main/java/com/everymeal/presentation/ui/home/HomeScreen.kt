package com.everymeal.presentation.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
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
import androidx.hilt.navigation.compose.hiltViewModel
import com.everymeal.presentation.R
import com.everymeal.presentation.components.EveryMealLineButton
import com.everymeal.presentation.components.EveryMealMainBottomSheetDialog
import com.everymeal.presentation.components.EveryMealRestaurantItem
import com.everymeal.presentation.components.EveryMealReviewItem
import com.everymeal.presentation.ui.theme.EveryMealTypography
import com.everymeal.presentation.ui.theme.EveryMeal_AndroidTheme
import com.everymeal.presentation.ui.theme.Gray100
import com.everymeal.presentation.ui.theme.Gray300
import com.everymeal.presentation.ui.theme.Gray500
import com.everymeal.presentation.ui.theme.Gray800
import com.everymeal.presentation.ui.theme.Paddings

@Composable
fun HomeScreen(
    homeViewModel : HomeViewModel = hiltViewModel(),
    onDetailScreenClickType : (String) -> Unit,
) {
    val items = listOf(
        Restaurant(
            name = "슈니",
            category = "한식",
            image = listOf(
                R.drawable.food_ex_1,
            ),
            rating = 4.5,
            reviewCount = 100,
            loveCount = 100,
        ),
        Restaurant(
            name = "왕가주방",
            category = "중식",
            image = listOf(
                R.drawable.food_ex_1,
                R.drawable.food_ex_2,
                R.drawable.food_ex_3,
                R.drawable.food_ex_3,
            ),
            rating = 4.5,
            reviewCount = 100,
            loveCount = 100,
        ),
    )

    val homeViewState by homeViewModel.viewState.collectAsState()

    if (homeViewState.bottomSheetState) {
        EveryMealMainBottomSheetDialog(
            title = stringResource(id = R.string.univ_admin_review_title),
            content = stringResource(id = R.string.univ_admin_review_content),
            onClick = {

            },
            onDismiss = {
                homeViewModel.setEvent(HomeContract.HomeEvent.BottomSheetStateChange(false))
            }
        )
    }

    val reviewTestItem = listOf(
        Review(
            name = "슈니",
            profileImage = R.drawable.profile_ex_image,
            loveCount = 100,
            image = listOf(
                R.drawable.review_ex_1,
                R.drawable.review_ex_1,
            ),
            rating = 5,
            reviewDate = "2023-08-29T09:58:47.604732",
            content = "매장 안쪽으로 가면 너무 감성있는 곳이 나와요. 그리고 분위기도 너무 좋고 맛도 너무 완벽해요. 이런 카페는 정말 처음인 것 같아요. 알바생도 너무 아름답습니다.. 여기 계속 찾을 것 같아요. 정말 항상 감사드려요.",
            restaurantName = "왕가주방",
        ),
        Review(
            name = "슈니",
            profileImage = R.drawable.profile_ex_image,
            loveCount = 100,
            image = listOf(
                R.drawable.review_ex_1,
                R.drawable.review_ex_1,
            ),
            rating = 5,
            reviewDate = "2023-08-20T09:58:47.604732",
            content = "맛있어요",
            restaurantName = "왕가주방",
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
                HomeMainTopLayout {
                    homeViewModel.setEvent(HomeContract.HomeEvent.BottomSheetStateChange(true))
                }
                HomeCategoryList {
                    onDetailScreenClickType(it)
                    homeViewModel.setEvent(HomeContract.HomeEvent.OnClickDetailList(it.DetailListScreenType()))
                }
                Spacer(modifier = Modifier.padding(10.dp))

                HomeDivider()
                Spacer(modifier = Modifier.padding(10.dp))

                LazyColumnTitle(stringResource(R.string.home_top_good_restaurant))
                Spacer(modifier = Modifier.padding(8.dp))
            }
            items(items.size) { index ->
                val item = items[index]
                EveryMealRestaurantItem(item) {

                }
                Spacer(modifier = Modifier.padding(10.dp))
                if (index != items.size - 1) {
                    Divider(
                        modifier = Modifier
                            .padding(horizontal = 20.dp),
                        color = Gray300,
                    )
                }
                Spacer(modifier = Modifier.padding(10.dp))
            }
            item {
                EveryMealLineButton(
                    text = stringResource(R.string.home_restaurant_button_text),
                    onClick = {

                    },
                )
            }

            item {
                Spacer(modifier = Modifier.padding(2.dp))
                HomeDivider()
                Spacer(modifier = Modifier.padding(10.dp))

                LazyColumnTitle(stringResource(R.string.home_top_good_review))
                Spacer(modifier = Modifier.padding(8.dp))
            }
            items(reviewTestItem.size) { index ->
                val item = reviewTestItem[index]
                EveryMealReviewItem(item) {

                }
                Spacer(modifier = Modifier.padding(10.dp))
                if (index != reviewTestItem.size - 1) {
                    Divider(
                        modifier = Modifier
                            .padding(horizontal = 20.dp),
                        color = Gray300,
                    )
                }
                Spacer(modifier = Modifier.padding(10.dp))
            }
            item {
                EveryMealLineButton(
                    text = stringResource(R.string.home_restaurant_review_button_text),
                    onClick = {

                    },
                )
            }
        }
    }

    LaunchedEffect(key1 = homeViewModel.effect) {
        homeViewModel.effect.collect { effect ->
            when (effect) {
                is HomeContract.HomeEffect.NavigateToDetailListScreen -> {
                    onDetailScreenClickType(effect.detailListScreenType.title())
                }
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
fun HomeMainTopLayout(
    onClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
            .background(Gray300, RoundedCornerShape(12.dp))
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) {
                onClick()
            }
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
                style = EveryMealTypography.displaySmall,
                color = Gray800
            )
            Text(
                text = stringResource(R.string.home_top_category_sub_title),
                style = EveryMealTypography.labelSmall,
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
fun HomeCategoryList(
    isBottomSheet : Boolean = false,
    onClick: (String) -> Unit
) {
    val horizotalDp = if (isBottomSheet) 0.dp else 20.dp

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = horizotalDp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        CategoryItem(
            R.drawable.ic_homemenu_recommend,
            R.string.home_top_category_recommend
        ) {
            onClick("추천")
        }
        CategoryItem(
            R.drawable.ic_homemenu_bap,
            R.string.home_top_category_rice
        ) {
            onClick("밥집")
        }
        CategoryItem(
            R.drawable.ic_homemenu_cake,
            R.string.home_top_category_cafe
        ) {
            onClick("카페")
        }
        CategoryItem(
            R.drawable.ic_homemenu_beer,
            R.string.home_top_category_drink
        ) {
            onClick("술집")
        }
    }
}

@Composable
fun HomeDivider() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Gray100)
            .height(12.dp)
    )
}

@Composable
fun LazyColumnTitle(title: String) {
    Text(
        modifier = Modifier.padding(start = 20.dp),
        text = title,
        fontSize = 20.sp,
        color = Color.Black,
        fontWeight = Bold,
    )
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
        HomeScreen() {

        }
    }
}