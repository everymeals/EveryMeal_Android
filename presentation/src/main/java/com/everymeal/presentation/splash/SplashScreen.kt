package com.everymeal.presentation.splash

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.everymeal.presentation.ExampleFoodState
import com.everymeal.presentation.ExampleViewModel
import com.everymeal.presentation.R
import com.everymeal.presentation.ui.theme.EveryMeal_AndroidTheme

@Composable
fun SplashScreen(
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

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.every_meal_logo_image),
            contentDescription = stringResource(R.string.app_name),
            modifier = Modifier.size(80.dp)
        )
        Spacer(modifier = Modifier.size(8.dp))
        Text(
            text = "everymeal",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SplashPreview() {
    EveryMeal_AndroidTheme {
        SplashScreen()
    }
}