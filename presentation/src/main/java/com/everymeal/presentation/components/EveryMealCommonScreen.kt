package com.everymeal.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.everymeal.presentation.ui.home.Restaurant

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

        ) {
            Text(
                text = restaurant.name,
                color = Color.Black,
                fontSize = 17.sp,
                fontWeight = FontWeight.SemiBold,
            )
        }
    }
}

@Preview
@Composable
fun EveryMealRestaurantItemPreview() {
    EveryMealRestaurantItem(Restaurant(
        name = "슈니",
        category = "한식",
        image = listOf(
            1,
            2,
            3,
        ),
        rating = 4.5f,
        reviewCount = 100,
        loveCount = 100,
    ))
}