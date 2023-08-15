package com.everymeal.presentation.ui.save

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.everymeal.presentation.R
import com.everymeal.presentation.ui.theme.Grey2
import com.everymeal.presentation.ui.theme.Grey7
import com.everymeal.presentation.ui.theme.Main100
import com.everymeal.presentation.ui.theme.RED_LIGHT
import com.everymeal.presentation.ui.theme.Typography

@Composable
fun SaveScreen() {
    Scaffold(
        topBar = {
            SaveTopBar()
        }
    ) { innerPadding ->

        val elements by remember {
            mutableStateOf(
                listOf(
                    ChipState("전체", mutableStateOf(false)),
                    ChipState("밥집", mutableStateOf(false)),
                    ChipState("카페", mutableStateOf(false)),
                    ChipState("술", mutableStateOf(false))
                )
            )
        }

        Column(modifier = Modifier.padding(innerPadding)) {
            Chips(
                modifier = Modifier.padding(start = 20.dp),
                elements = elements,
                onChipClicked = { content, isSelected, idx ->
                    elements[idx].isSelected.value = !elements[idx].isSelected.value
                }
            )
        }
    }
}

data class ChipState(
    var text: String,
    val isSelected: MutableState<Boolean>
)

@Composable
private fun Chip(
    text: String,
    selected: Boolean,
    modifier: Modifier = Modifier,
    onChipClicked: (String, Boolean) -> Unit,
) {
    Surface(
        color = when {
            selected -> RED_LIGHT
            else -> Grey2
        },
        shape = RoundedCornerShape(100.dp),
        modifier = modifier
    ) {
        Text(
            text = text,
            color = when {
                selected -> Main100
                else -> Grey7
            },
            style = Typography.bodySmall,
            modifier = Modifier
                .padding(horizontal = 12.dp, vertical = 6.dp)
                .clickable { onChipClicked(text, selected) }
        )
    }
}

@Composable
fun Chips(
    modifier: Modifier = Modifier,
    elements: List<ChipState>,
    onChipClicked: (String, Boolean, Int) -> Unit,
) {
    LazyRow(modifier = modifier) {
        items(elements.size) { idx ->
            Chip(
                text = elements[idx].text,
                selected = elements[idx].isSelected.value,
                onChipClicked = { content, isSelected ->
                    onChipClicked(content, isSelected, idx)
                }
            )
            Spacer(modifier = Modifier.padding(8.dp))
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
@Preview
fun SaveScreenPreview() {
    SaveScreen()
}