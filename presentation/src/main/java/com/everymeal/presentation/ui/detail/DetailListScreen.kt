package com.everymeal.presentation.ui.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.everymeal.presentation.R
import com.everymeal.presentation.ui.save.SaveTopBar
import com.everymeal.presentation.ui.theme.Grey2
import com.everymeal.presentation.ui.theme.Grey7
import com.everymeal.presentation.ui.theme.Typography

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
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(horizontal = 20.dp),
            ) {
                DetailScreenChip(
                    title = "최신순",
                    isCategory = true,
                    onChipClicked = {

                    }
                )
            }
        }
    }
}

@Composable
fun DetailScreenChip(
    title: String,
    isCategory: Boolean,
    onChipClicked: () -> Unit,
) {
    Surface(
        color = Grey2,
        shape = RoundedCornerShape(100.dp),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = title,
                color = Grey7,
                style = Typography.bodySmall,
                modifier = Modifier
                    .padding(start = 12.dp, end = 4.dp, top = 6.dp, bottom = 6.dp)
                    .clickable { onChipClicked() }
            )
            Image(
                modifier = Modifier
                    .padding(end = 12.dp)
                    .size(12.dp),
                imageVector = ImageVector.vectorResource(id = R.drawable.icon_arrow_bottom),
                contentDescription = "arrow_bottom"
            )
        }
    }
}

@Preview
@Composable
fun PreviewDetailListScreen() {
    DetailListScreen("맛집")
}

@Preview
@Composable
fun PreviewDetailScreenChip() {
    DetailScreenChip(
        title = "최신순",
        isCategory = true,
        onChipClicked = {

        }
    )
}