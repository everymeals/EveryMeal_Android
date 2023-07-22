package com.everymeal.presentation.ui.splash

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.everymeal.presentation.signup.UnivSelectActivity
import com.everymeal.presentation.theme.EveryMeal_AndroidTheme
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
                    UnivSelectActivity.startActivity(this)
                    finish()
                })
            }
        }
    }
}