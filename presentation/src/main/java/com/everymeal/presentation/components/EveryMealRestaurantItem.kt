package com.everymeal.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.everymeal.presentation.R
import com.everymeal.presentation.ui.home.HomeScreen
import com.everymeal.presentation.ui.home.Restaurant
import com.everymeal.presentation.ui.theme.EveryMeal_AndroidTheme
import com.everymeal.presentation.ui.theme.Gray300
import com.everymeal.presentation.ui.theme.Gray500
import com.everymeal.presentation.ui.theme.Gray600
import com.everymeal.presentation.ui.theme.Gray700

@Composable
fun EveryMealRestaurantItem(
    restaurant: Restaurant,
    onLoveClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp)
            .background(color = Color.White)
    ) {
        RestaurantTitle(restaurant)
        Spacer(modifier = Modifier.padding(4.dp))
        RestaurantInfo(restaurant)
        Spacer(modifier = Modifier.padding(4.dp))
        RestaurantImage(restaurant)
    }
}

@Composable
fun RestaurantTitle(restaurant: Restaurant) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = restaurant.name,
            color = Color.Black,
            fontSize = 17.sp,
            fontWeight = FontWeight.SemiBold,
        )
        Text(
            modifier = Modifier
                .padding(start = 4.dp)
                .clip(RoundedCornerShape(4.dp))
                .background(color = Gray300)
                .padding(vertical = 3.dp, horizontal = 6.dp),
            text = restaurant.category,
            color = Gray600,
            fontSize = 12.sp
        )
        Spacer(modifier = Modifier.weight(1f))
        Image(
            modifier = Modifier
                .padding(start = 4.dp),
            imageVector = ImageVector.vectorResource(R.drawable.icon_heart_mono),
            contentDescription = stringResource(R.string.icon_star),
        )
    }
}

@Composable
fun RestaurantInfo(restaurant: Restaurant) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            imageVector = ImageVector.vectorResource(R.drawable.icon_star_mono),
            contentDescription = stringResource(R.string.icon_star),
        )
        Text(
            modifier = Modifier.padding(start = 2.dp),
            text = restaurant.rating.toString(),
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
        Text(
            text = "${restaurant.loveCount}",
            color = Gray500,
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
        )
    }
}

@Composable
fun RestaurantImage(restaurant: Restaurant) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        when {
            restaurant.image.size == 3 -> {
                restaurant.image.forEachIndexed { index, image ->
                    Image(
                        modifier = Modifier
                            .weight(1f)
                            .aspectRatio(1f)
                            .clip(RoundedCornerShape(8.dp)),
                        painter = painterResource(id = image),
                        contentDescription = null
                    )
                    if(index != 2) {
                        Spacer(modifier = Modifier.padding(end = 6.dp))
                    }
                }
            }

            restaurant.image.size == 2 -> {
                restaurant.image.forEach { image ->
                    Image(
                        modifier = Modifier
                            .weight(1f)
                            .aspectRatio(1f)
                            .clip(RoundedCornerShape(8.dp)),
                        painter = painterResource(id = image),
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.padding(6.dp))
                }
                Spacer(modifier = Modifier.weight(1f))
            }

            restaurant.image.size == 1 -> {
                Image(
                    modifier = Modifier
                        .weight(1f)
                        .aspectRatio(1f)
                        .clip(RoundedCornerShape(8.dp)),
                    painter = painterResource(id = restaurant.image[0]),
                    contentDescription = null
                )
                Spacer(modifier = Modifier.weight(2f))
            }

            restaurant.image.size > 3 -> {
                Image(
                    modifier = Modifier
                        .weight(1f)
                        .aspectRatio(1f)
                        .clip(RoundedCornerShape(8.dp)),
                    painter = painterResource(restaurant.image[0]),
                    contentDescription = null
                )
                Image(
                    modifier = Modifier
                        .weight(1f)
                        .aspectRatio(1f)
                        .clip(RoundedCornerShape(8.dp)),
                    painter = painterResource(restaurant.image[1]),
                    contentDescription = null
                )
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .aspectRatio(1f)
                ) {
                    Image(
                        modifier = Modifier
                            .aspectRatio(1f)
                            .fillMaxSize(),
                        painter = painterResource(id = restaurant.image[2]),
                        contentDescription = null
                    )
                    Box(
                        modifier = Modifier
                            .matchParentSize()
                            .clip(RoundedCornerShape(8.dp))
                            .background(Color.Black.copy(alpha = 0.5f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "+${restaurant.image.size - 2}",
                            color = Color.White,
                            fontSize = 14.sp
                        )
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
        HomeScreen()
    }
}