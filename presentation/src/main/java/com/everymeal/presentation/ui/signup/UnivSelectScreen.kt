package com.everymeal.presentation.ui.signup

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.everymeal.presentation.R
import com.everymeal.presentation.components.DisableButton
import com.everymeal.presentation.theme.EveryMeal_AndroidTheme
import com.everymeal.presentation.theme.Gray100

data class Item(
    val Image: Int,
    val name: String,
)

@Composable
fun UnivSelectScreen(
    onSelectClick : () -> Unit
) {
    val items = listOf(
        Item(Image = R.drawable.image_myongji, name = "명지대"),
        Item(Image = R.drawable.image_sungsin, name = "성신여대"),
        Item(Image = R.drawable.image_seoulwoman, name = "서울여대"),
        Item(Image = R.drawable.image_konkuk, name = "건국대"),
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp)
        ) {
            Spacer(modifier = Modifier.padding(70.dp))
            Text(
                text = stringResource(R.string.univ_select_title),
                style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                ),
            )
            Spacer(modifier = Modifier.padding(10.dp))
            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                modifier = Modifier.weight(1f),
            ) {
                items(items.size) { index ->
                    UnivSelectItem(item = items[index])
                }
            }
            DisableButton(
                text = stringResource(R.string.select),
            )
        }
    }
}


@Composable
fun UnivSelectItem(item: Item) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(Gray100)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.padding(bottom = 10.dp))
        Image(
            painter = painterResource(item.Image),
            contentDescription = item.name,
            Modifier.size(36.dp)
        )
        Text(
            text = item.name,
            fontSize = 14.sp,
            modifier = Modifier.padding(10.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun UnivSelectScreenPreview() {
    EveryMeal_AndroidTheme {
        UnivSelectScreen{ }
    }
}

@Preview(showBackground = true)
@Composable
fun UnivSelectScreenItemPreview() {
    EveryMeal_AndroidTheme {
        UnivSelectItem(item = Item(
            Image = R.drawable.image_myongji,
            name = "명지대학교"
        ))
    }
}