package com.everymeal.presentation.ui.onboarding

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.everymeal.presentation.R
import com.everymeal.presentation.components.EveryMealMainButton
import com.everymeal.presentation.ui.theme.EveryMealTypography
import com.everymeal.presentation.ui.theme.Gray300
import com.everymeal.presentation.ui.theme.Main100

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardingScreen(
    onNavigateToUnivSelect: () -> Unit
) {
    val onBoardingList = listOf(
        Triple(R.drawable.icon_onboarding_image_bowel, 160.dp, stringResource(id = R.string.onboarding_today_what_food)),
        Triple(R.drawable.icon_onboarding_image_bowel, 160.dp, "여기는 로티가 들어가요"),
        Triple(R.drawable.icon_onboarding_image_school, 144.dp, stringResource(id = R.string.onboarding_school_food)),
        Triple(R.drawable.icon_onboarding_image_logo, 140.dp, stringResource(id = R.string.onboarding_everymeal)),
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp),
    ) {
        val pageCount = 4
        val pagerState = rememberPagerState(
            pageCount = { pageCount },
        )
        Box(
            modifier = Modifier.weight(1f),
            contentAlignment=Alignment.Center
        ) {
            HorizontalPager(
                state = pagerState,
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(id = onBoardingList[it].first),
                        contentDescription = "onboarding",
                        modifier = Modifier
                            .size(onBoardingList[it].second)
                    )
                    Spacer(modifier = Modifier.size(20.dp))
                    Text(
                        text = onBoardingList[it].third,
                        style = EveryMealTypography.titleLarge,
                        fontSize = 22.sp,
                        textAlign = TextAlign.Center,
                        lineHeight = 30.sp
                    )
                }
            }
        }

        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(pageCount) { iteration ->
                val color = if (pagerState.currentPage == iteration) Main100 else Gray300
                Box(
                    modifier = Modifier
                        .padding(8.dp)
                        .clip(RoundedCornerShape(50))
                        .background(color)
                        .size(10.dp)
                )
            }
        }
        Spacer(modifier = Modifier.size(30.dp))
        EveryMealMainButton(
            text = stringResource(id = R.string.next),
            onClick = onNavigateToUnivSelect
        )
    }
}

@Preview
@Composable
fun OnboardingScreenPreview() {
    OnboardingScreen(onNavigateToUnivSelect = {})
}