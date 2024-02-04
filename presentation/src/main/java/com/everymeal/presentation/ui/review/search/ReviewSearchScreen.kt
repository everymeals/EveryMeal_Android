package com.everymeal.presentation.ui.review.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.everymeal.presentation.R
import com.everymeal.presentation.ui.bottom.EveryMealRoute
import com.everymeal.presentation.ui.review.ReviewTopBar
import com.everymeal.presentation.ui.theme.Typography

@Composable
fun ReviewSearchScreen(
    navController: NavController,
) {
    Scaffold(
        topBar = {
            ReviewTopBar(
                title = stringResource(R.string.review_write),
                onBackClicked = {
                    navController.popBackStack()
                },
            )
        },
        containerColor = Color.White,
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            Column {
                ReviewGuideHeader()
                Image(
                    modifier = Modifier
                        .padding(top = 28.dp)
                        .padding(horizontal = 20.dp)
                        .clickable {
                            navController.navigate(EveryMealRoute.SEARCH.route)
                        },
                    painter = painterResource(id = R.drawable.img_review_searchbar),
                    contentDescription = null
                )
            }
        }
    }
}

@Composable
fun ReviewGuideHeader(
    modifier: Modifier = Modifier,
) {
    Text(
        modifier = modifier
            .padding(start = 24.dp, top = 48.dp),
        text = stringResource(R.string.review_guide_header),
        style = Typography.titleLarge,
    )
}
