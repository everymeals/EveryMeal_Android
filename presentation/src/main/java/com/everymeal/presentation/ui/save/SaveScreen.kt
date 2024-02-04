package com.everymeal.presentation.ui.save

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.everymeal.presentation.R
import com.everymeal.presentation.ui.save.chip.ChipStyle
import com.everymeal.presentation.ui.save.chip.Chips
import com.everymeal.presentation.ui.theme.EveryMealTypography
import com.everymeal.presentation.ui.theme.Gray800
import com.everymeal.presentation.ui.theme.Grey2
import com.everymeal.presentation.ui.theme.Grey7
import com.everymeal.presentation.ui.theme.Main100
import com.everymeal.presentation.ui.theme.RED_LIGHT
import com.everymeal.presentation.ui.theme.Typography

val chipStyle: ChipStyle = ChipStyle(
    selectedColor = RED_LIGHT,
    unselectedColor = Grey2,
    chipTextStyle = Typography.bodySmall,
    selectedTextColor = Main100,
    unselectedTextColor = Grey7,
    chipModifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
)

@Composable
fun SaveScreen(
    viewModel: SaveScreenViewModel = hiltViewModel(),
) {
    val viewState by viewModel.viewState.collectAsState()

    Scaffold(
        topBar = {
            SaveTopBar()
        }
    ) { innerPadding ->
        Chips(
            modifier = Modifier
                .padding(start = 20.dp)
                .padding(innerPadding),
            elements = viewState.chipElements,
            chipStyle = chipStyle,
            onChipClicked = { _, _, chipIndex ->
                viewModel.setEvent(SaveEvent.OnChipClicked(chipIndex))
            }
        )
        EmptyView(modifier = Modifier.fillMaxSize())
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SaveTopBar(
    title: String = stringResource(id = R.string.save_title),
    onBackClick: () -> Unit = {}
) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.White,
        ),
        title = {
            Text(
                text = title,
                style = EveryMealTypography.Subtitle2
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
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                painter = painterResource(id = R.drawable.icon_save_empty),
                contentDescription = "empty icon",
            )
            Spacer(modifier = Modifier.padding(top = 8.dp))
            Text(
                text = stringResource(id = R.string.save_empty_description),
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
}

@Composable
@Preview
fun SaveScreenPreview() {
    SaveScreen()
}
