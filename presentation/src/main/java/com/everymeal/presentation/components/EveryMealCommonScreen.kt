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
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.everymeal.presentation.R
import com.everymeal.presentation.ui.home.Restaurant
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
        Spacer(modifier = Modifier.padding(4.dp))
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
        Spacer(modifier = Modifier.padding(4.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            when {
                restaurant.image.size == 3 -> {
                    restaurant.image.forEach { image ->
                        Image(
                            modifier = Modifier
                                .weight(1f)
                                .aspectRatio(1f)
                                .padding(4.dp),
                            painter = painterResource(id = image),
                            contentDescription = null
                        )
                    }
                }
                restaurant.image.size == 2 -> {
                    restaurant.image.forEach { image ->
                        Image(
                            modifier = Modifier
                                .weight(1f)
                                .aspectRatio(1f)
                                .padding(4.dp),
                            painter = painterResource(id = image),
                            contentDescription = null
                        )
                    }
                    Spacer(modifier = Modifier.weight(1f)) // 여기서 남은 공간을 차지
                }
                restaurant.image.size == 1 -> {
                    Image(
                        modifier = Modifier
                            .weight(1f)
                            .aspectRatio(1f)
                            .padding(4.dp),
                        painter = painterResource(id = restaurant.image[0]),
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.weight(2f)) // 2개의 공간을 비워둡니다
                }
                restaurant.image.size > 3 -> {
                    Image(
                        modifier = Modifier
                            .weight(1f)
                            .aspectRatio(1f)
                            .padding(4.dp),
                        painter = painterResource(restaurant.image[0]),
                        contentDescription = null
                    )
                    Image(
                        modifier = Modifier
                            .weight(1f)
                            .aspectRatio(1f)
                            .padding(4.dp),
                        painter = painterResource(restaurant.image[1]),
                        contentDescription = null
                    )
                    Box(
                        modifier = Modifier
                            .aspectRatio(1f)
                            .padding(4.dp)
                    ) {
                        Image(
                            painter = painterResource(id = restaurant.image[2]),
                            contentDescription = null,
                            colorFilter = ColorFilter.tint(Color.Gray.copy(alpha = 0.6f))
                        )
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier.matchParentSize()
                        ) {
                            Text(text = "+${restaurant.image.size - 2}", color = Color.White, fontSize = 14.sp)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ImageComponent(imageRes: Int, weight: Float) {
    Image(
        modifier = Modifier
            .aspectRatio(1f)
            .padding(4.dp),
        painter = painterResource(id = imageRes),
        contentDescription = null
    )
}

@Composable
fun DimImageComponent(imageRes: Int, count: Int) {
    Box(
        modifier = Modifier
            .aspectRatio(1f)
            .padding(4.dp)
    ) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = null,
            colorFilter = ColorFilter.tint(Color.Gray.copy(alpha = 0.6f))
        )
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.matchParentSize()
        ) {
            Text(text = "+$count", color = Color.White, fontSize = 14.sp)
        }
    }
}

@Preview
@Composable
fun EveryMealRestaurantItemPreview() {
    EveryMealRestaurantItem(
        Restaurant(
            name = "슈니",
            category = "한식",
            image = listOf(
                1,
                2,
                3,
            ),
            rating = 4.5,
            reviewCount = 100,
            loveCount = 50,
        )
    ) {

    }
}