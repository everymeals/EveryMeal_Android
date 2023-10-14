package com.everymeal.presentation.ui.splash

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.everymeal.presentation.ui.onboarding.OnboardingActivity
import com.everymeal.presentation.ui.theme.EveryMeal_AndroidTheme
import com.everymeal.presentation.ui.signup.UnivSelectActivity
import dagger.hilt.android.AndroidEntryPoint

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setSplashScreen()
    }

    private fun setSplashScreen() {
        setContent {
            EveryMeal_AndroidTheme {
                SplashScreen(onFinishSplash = {
                    OnboardingActivity.startActivity(this)
                    finish()
                })
            }
        }
    }
}