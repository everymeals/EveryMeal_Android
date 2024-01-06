package com.everymeal.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.everymeal.domain.model.restaurant.RestaurantDataEntity
import com.everymeal.presentation.R
import com.everymeal.presentation.ui.home.HomeScreen
import com.everymeal.presentation.ui.theme.EveryMeal_AndroidTheme
import com.everymeal.presentation.ui.theme.Gray300
import com.everymeal.presentation.ui.theme.Gray500
import com.everymeal.presentation.ui.theme.Gray600
import com.everymeal.presentation.ui.theme.Gray700

@Composable
fun EveryMealRestaurantItem(
    restaurant: RestaurantDataEntity,
    onLoveClick: () -> Unit = {},
    onDetailClick: () -> Unit = {},
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) {
                onDetailClick()
            }
            .padding(horizontal = 20.dp)
            .background(color = Color.White)
    ) {
        RestaurantTitle(Modifier.fillMaxWidth(), restaurant) {
            onLoveClick()
        }
        RestaurantRating(restaurant)
        Spacer(modifier = Modifier.padding(4.dp))
        RestaurantImage(restaurant)
    }
}

@Composable
fun RestaurantTitle(
    modifier: Modifier = Modifier,
    restaurant: RestaurantDataEntity,
    onLoveClick: () -> Unit,
) {
    Row(
        modifier = modifier,
    ) {
        Text(
            modifier = Modifier.padding(top = 6.dp),
            text = restaurant.name,
            color = Color.Black,
            fontSize = 17.sp,
            fontWeight = FontWeight.SemiBold,
            maxLines = 1
        )
        Text(
            modifier = Modifier
                .padding(start = 4.dp, top = 7.dp)
                .clip(RoundedCornerShape(4.dp))
                .background(color = Gray300)
                .padding(vertical = 3.dp, horizontal = 6.dp),
            text = restaurant.categoryDetail,
            color = Gray600,
            fontSize = 12.sp
        )
        Spacer(modifier = Modifier.weight(1f))
        RestaurantLoveCount(restaurant, onLoveClick)
    }
}

@Composable
fun RestaurantLoveCount(
    restaurant: RestaurantDataEntity,
    onLoveClick: () -> Unit,
) {
    Column(
        modifier = Modifier.clickable(
            indication = null,
            interactionSource = remember { MutableInteractionSource() }
        ) { onLoveClick() },
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            modifier = Modifier,
            imageVector = ImageVector.vectorResource(R.drawable.icon_heart_mono),
            contentDescription = stringResource(R.string.icon_star),
        )
        Text(
            text = "${restaurant.recommendedCount}",
            color = Gray500,
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
        )
    }
}

@Composable
fun RestaurantRating(restaurant: RestaurantDataEntity) {
    Row(
        modifier = Modifier
            .width(100.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            imageVector = ImageVector.vectorResource(R.drawable.icon_active_star_mono),
            contentDescription = stringResource(R.string.icon_star),
        )
        Text(
            modifier = Modifier.padding(start = 2.dp),
            text = restaurant.grade.toString(),
            color = Gray700,
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
        )
        Text(
            modifier = Modifier.padding(start = 2.dp),
            text = "(${restaurant.reviewCount})",
            color = Gray700,
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
        )
        Spacer(modifier = Modifier.weight(1f))
    }
}

@Composable
fun RestaurantImage(restaurant: RestaurantDataEntity) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        restaurant.images?.let {
            when {
                restaurant.images?.size == 3 -> {
                    restaurant.images?.forEachIndexed { index, image ->
                        AsyncImage(
                            modifier = Modifier
                                .weight(1f)
                                .aspectRatio(1f)
                                .clip(RoundedCornerShape(8.dp)),
                            model = image,
                            contentDescription = null
                        )
                        if(index != 2) {
                            Spacer(modifier = Modifier.padding(end = 6.dp))
                        }
                    }
                }

                restaurant.images?.size == 2 -> {
                    restaurant.images?.forEachIndexed { index, image ->
                        AsyncImage(
                            modifier = Modifier
                                .weight(1f)
                                .aspectRatio(1f)
                                .clip(RoundedCornerShape(8.dp)),
                            model = image,
                            contentDescription = null
                        )
                        if(index != 1) {
                            Spacer(modifier = Modifier.padding(end = 6.dp))
                        }
                    }
                    Spacer(modifier = Modifier.weight(1f))
                }

                restaurant.images?.size == 1 -> {
                    AsyncImage(
                        modifier = Modifier
                            .weight(1f)
                            .aspectRatio(1f)
                            .clip(RoundedCornerShape(8.dp)),
                        model = restaurant.images!![0],
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier
                        .weight(2f)
                        .padding(end = 6.dp)
                    )
                }

                restaurant.images?.size!! > 3 -> {
                    AsyncImage(
                        modifier = Modifier
                            .weight(1f)
                            .aspectRatio(1f)
                            .clip(RoundedCornerShape(8.dp)),
                        model = restaurant.images!![0],
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.padding(end = 6.dp))
                    AsyncImage(
                        modifier = Modifier
                            .weight(1f)
                            .aspectRatio(1f)
                            .clip(RoundedCornerShape(8.dp)),
                        model = restaurant.images!![0],
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.padding(end = 6.dp))
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .aspectRatio(1f)
                    ) {
                        AsyncImage(
                            modifier = Modifier
                                .aspectRatio(1f)
                                .fillMaxSize(),
                            model = restaurant.images!![0],
                            contentDescription = null
                        )
                        Box(
                            modifier = Modifier
                                .matchParentSize()
                                .clip(RoundedCornerShape(8.dp))
                                .background(Color.Black.copy(alpha = 0.2f)),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "+${restaurant.reviewCount - 2}",
                                color = Color.White,
                                fontSize = 14.sp
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    EveryMeal_AndroidTheme {
        HomeScreen(
            onDetailScreenClickType = {},
            onDetailRestaurantClick = {},
        )
    }
}
