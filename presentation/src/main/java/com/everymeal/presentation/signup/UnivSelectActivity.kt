package com.everymeal.presentation.signup

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
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