package com.everymeal.presentation.ui.onboarding

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.everymeal.presentation.ui.main.MainActivity
import com.everymeal.presentation.ui.signup.UnivSelectActivity
import com.everymeal.presentation.ui.theme.EveryMeal_AndroidTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class OnboardingActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setUnivSelectScreen()
    }

    private fun setUnivSelectScreen() {
        setContent {
            EveryMeal_AndroidTheme {
                OnboardingScreen{
                    UnivSelectActivity.startActivity(this)
                }
            }
        }
    }

    companion object {
        fun startActivity(context: Context) {
            val intent = Intent(context, OnboardingActivity::class.java)
            context.startActivity(intent)
        }
    }
}