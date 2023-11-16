package com.everymeal.presentation.ui.splash

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import com.everymeal.presentation.ui.theme.EveryMeal_AndroidTheme

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun SplashScreen(
    viewModel : SplashViewModel = hiltViewModel(),
    onFinishSplash: () -> Unit,
) {
    val composition by rememberLottieComposition(LottieCompositionSpec.Asset("everymeal_splash.json"))
    val progress by animateLottieCompositionAsState(composition)

    LaunchedEffect(Unit) {
        viewModel.setEvent(SplashContract.SplashEvent.InitSplashScreen)
    }

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