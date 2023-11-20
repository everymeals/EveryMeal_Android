package com.everymeal.presentation.ui.splash

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.everymeal.presentation.ui.main.MainActivity
import com.everymeal.presentation.ui.onboarding.OnboardingActivity
import com.everymeal.presentation.ui.theme.EveryMeal_AndroidTheme
import dagger.hilt.android.AndroidEntryPoint

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashActivity : ComponentActivity() {

    private val viewModel: SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setSplashScreen()
    }

    private fun setSplashScreen() {
        setContent {
            EveryMeal_AndroidTheme {
                SplashScreen(onFinishSplash = {
                    if(viewModel.viewState.value.selectedUnitIndex.isNullOrBlank()) {
                        OnboardingActivity.startActivity(this)
                        finish()
                    } else {
                        MainActivity.startActivity(this)
                        finish()
                    }
                })
            }
        }
    }
}