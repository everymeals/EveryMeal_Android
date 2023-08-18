package com.everymeal.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.everymeal.presentation.R
import com.everymeal.presentation.ui.home.Restaurant
import com.everymeal.presentation.ui.theme.Gray300
import com.everymeal.presentation.ui.theme.Gray600
import com.everymeal.presentation.ui.theme.Gray700

@Composable
fun EveryMealRestaurantItem(
    restaurant: Restaurant
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp)
            .background(color = Color.White)
    ) {
        Row(
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
        }
        Spacer(modifier = Modifier.padding(4.dp))
        Row(
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
            loveCount = 100,
        )
    )
}