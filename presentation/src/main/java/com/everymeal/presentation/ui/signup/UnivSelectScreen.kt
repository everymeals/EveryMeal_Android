package com.everymeal.presentation.ui.signup

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.everymeal.presentation.ExampleViewModel
import com.everymeal.presentation.R
import com.everymeal.presentation.components.EveryMealMainButton
import com.everymeal.presentation.ui.theme.EveryMeal_AndroidTheme
import com.everymeal.presentation.ui.theme.Gray100
import com.everymeal.presentation.ui.theme.Gray300
import com.everymeal.presentation.ui.theme.Gray500
import com.everymeal.presentation.ui.theme.Gray800
import com.everymeal.presentation.ui.theme.Paddings

data class Item(
    val Image: Int,
    val name: String,
)

@Composable
fun UnivSelectScreen(
    viewModel: UnivSelectViewModel = hiltViewModel(),
    onSelectClick : () -> Unit
) {
    val viewState by viewModel.viewState.collectAsState()

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
                .padding(horizontal = Paddings.extra)
        ) {
            Spacer(modifier = Modifier.padding(58.dp))
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
                    val item = items[index]
                    val isSelected = viewState.selectedUniv == item.name
                    UnivSelectItem(
                        item = item,
                        isSelected = isSelected,
                    ) {
                        viewModel.setEvent(UnivSelectContract.UnivSelectEvent.SelectedUniv(item.name))
                    }
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Gray300, RoundedCornerShape(100.dp))
                    .padding(horizontal = Paddings.extra, vertical = 14.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.icon_chat),
                    contentDescription = stringResource(id = R.string.icon_chat_description),
                    tint = Gray500
                )
                Spacer(modifier = Modifier.padding(14.dp))
                Column {
                    Text(
                        text = stringResource(id = R.string.univ_select_not_univ),
                        fontSize = 15.sp,
                        color = Gray800
                    )
                    Text(
                        text = stringResource(id = R.string.univ_select_apply),
                        fontSize = 14.sp,
                        color = Gray500
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.icon_arrow_right),
                    contentDescription = stringResource(id = R.string.icon_arrow_right),
                    tint = Gray500
                )
            }
            EveryMealMainButton(
                text = stringResource(R.string.select),
                enabled = false,
            ) {
                onSelectClick()
            }
        }
    }
}

@SuppressLint("RememberReturnType")
@Composable
fun UnivSelectItem(item: Item, isSelected: Boolean, onSelectClick: (Item) -> Unit) {
    Column(
        modifier = Modifier
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) { onSelectClick(item) }
            .padding(Paddings.medium)
            .clip(RoundedCornerShape(Paddings.medium))
            .background(if (isSelected) Gray500 else Gray100)
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
            ),
            false
        ) {

        }
    }
}