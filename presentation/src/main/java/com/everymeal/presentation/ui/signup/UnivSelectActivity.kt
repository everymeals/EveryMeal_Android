package com.everymeal.presentation.ui.signup

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.everymeal.presentation.theme.EveryMeal_AndroidTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class UnivSelectActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setUnivSelectScreen()
    }

    private fun setUnivSelectScreen() {
        setContent {
            EveryMeal_AndroidTheme {
                UnivSelectScreen()
            }
        }
    }

    companion object {
        fun startActivity(context: Context) {
            val intent = Intent(context, UnivSelectActivity::class.java)
            context.startActivity(intent)
        }
    }
}