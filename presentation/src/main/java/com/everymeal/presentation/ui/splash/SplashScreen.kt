package com.everymeal.presentation.ui.splash

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.everymeal.presentation.ExampleFoodState
import com.everymeal.presentation.ExampleViewModel
import com.everymeal.presentation.theme.EveryMeal_AndroidTheme

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun SplashScreen(
    onFinishSplash: () -> Unit,
    viewModel: ExampleViewModel = hiltViewModel(),
) {
    LaunchedEffect(Unit) {
        viewModel.getWeekFood("MCC식당")
    }
    val weekFoodState by viewModel.weekGetFoodArea.collectAsState()

    when (weekFoodState) {
        is ExampleFoodState.UnInitialized -> {

        }

        is ExampleFoodState.Loading -> {

        }

        is ExampleFoodState.SuccessWeekFoodGetData -> {
            val data =
                (weekFoodState as ExampleFoodState.SuccessWeekFoodGetData).getWeekFoodData
            Log.d("clean architecture test url success", "$data")
        }

        is ExampleFoodState.Error -> {

        }
    }

    val composition by rememberLottieComposition(LottieCompositionSpec.Asset("everymeal_splash.json"))
    val progress by animateLottieCompositionAsState(composition)

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        LottieAnimation(
            composition = composition,
            modifier = Modifier.size(250.dp)
        )

        if (progress == 1f) {
            onFinishSplash()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SplashPreview() {
    EveryMeal_AndroidTheme {
        SplashScreen(
            onFinishSplash = { }
        )
    }
}