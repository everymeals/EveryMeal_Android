package com.everymeal.presentation.ui.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.everymeal.presentation.ui.save.SaveTopBar

@Composable
fun DetailListScreen(
    title: String,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White),
    ) {
        Scaffold(
            topBar = {
                SaveTopBar(title = title) {

                }
            }
        ) { innerPadding ->
            Spacer(modifier = Modifier.padding(innerPadding))

        }
    }
}

@Preview
@Composable
fun PreviewDetailListScreen() {
    DetailListScreen("맛집")
}