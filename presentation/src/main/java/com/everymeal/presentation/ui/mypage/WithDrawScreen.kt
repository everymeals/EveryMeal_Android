package com.everymeal.presentation.ui.mypage

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.everymeal.presentation.ui.save.SaveTopBar

@Composable
fun WithDrawScreen(
    onBackClick: () -> Unit = {}
) {
    Scaffold(
        topBar = {
            SaveTopBar(
                title = "탈퇴하기",
                onBackClick = onBackClick
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.padding(innerPadding)
        ) {

        }
    }
}

@Preview
@Composable
fun WithDrawScreenPreview() {
    WithDrawScreen()
}