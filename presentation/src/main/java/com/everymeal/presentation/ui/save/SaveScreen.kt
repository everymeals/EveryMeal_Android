package com.everymeal.presentation.ui.save

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.everymeal.presentation.R
import com.everymeal.presentation.ui.save.chip.Chips
import com.everymeal.presentation.ui.theme.Gray800

@Composable
fun SaveScreen(
    viewModel: SaveScreenViewModel = hiltViewModel(),
) {
    Scaffold(
        topBar = {
            SaveTopBar()
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Chips(
                modifier = Modifier.padding(start = 20.dp),
                elements = viewModel.elements,
                onChipClicked = { _, _, chipIndex ->
                    viewModel.updateChipState(chipIndex)
                }
            )
            EmptyView(
                modifier = Modifier
                    .fillMaxSize()
                    .align(
                        alignment = Alignment.CenterHorizontally,
                    )
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SaveTopBar(
    onBackClick: () -> Unit = {}
) {
    TopAppBar(title = {
        Text(
            text = "저장",
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        )
    },
        navigationIcon = {
            Image(
                modifier = Modifier
                    .padding(start = 20.dp, top = 12.dp, bottom = 12.dp, end = 10.dp)
                    .clickable {
                        onBackClick()
                    },
                painter = painterResource(id = R.drawable.icon_arrow_back_mono),
                contentDescription = "back"
            )
        }
    )
}

@Composable
fun EmptyView(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painter = painterResource(id = R.drawable.icon_save_empty),
            contentDescription = "empty icon",
        )
        Spacer(modifier = Modifier.padding(top = 8.dp))
        Text(
            text = "저장한 가게가 없어요",
            style = TextStyle(
                fontSize = 15.sp,
                lineHeight = 21.sp,
                fontWeight = FontWeight(500),
                color = Gray800,
                textAlign = TextAlign.Center,
            )
        )
    }
}

@Composable
@Preview
fun SaveScreenPreview() {
    SaveScreen()
}