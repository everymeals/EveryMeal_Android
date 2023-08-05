package com.everymeal.presentation.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.everymeal.presentation.R
import com.everymeal.presentation.ui.theme.EveryMeal_AndroidTheme
import com.everymeal.presentation.ui.theme.Gray300
import com.everymeal.presentation.ui.theme.Gray500
import com.everymeal.presentation.ui.theme.Gray800
import com.everymeal.presentation.ui.theme.Paddings


@Composable
fun HomeScreen(

) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White),
    ) {
        HomeTopAppBar()
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
        ) {
            HomeCategoryList()
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
fun HomeCategoryList() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Gray300, RoundedCornerShape(12.dp))
            .padding(horizontal = Paddings.extra, vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.ic_homemenu_recommend),
            contentDescription = stringResource(R.string.home_top_category_main_image),
            tint = Gray500
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

@Preview
@Composable
fun HomeScreenPreview() {
    EveryMeal_AndroidTheme {
        HomeScreen()
    }
}