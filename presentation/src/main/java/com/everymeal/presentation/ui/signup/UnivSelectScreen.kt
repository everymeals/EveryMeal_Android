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
import androidx.compose.runtime.LaunchedEffect
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
import com.everymeal.presentation.R
import com.everymeal.presentation.components.EveryMealMainButton
import com.everymeal.presentation.ui.theme.EveryMeal_AndroidTheme
import com.everymeal.presentation.ui.theme.Gray100
import com.everymeal.presentation.ui.theme.Gray300
import com.everymeal.presentation.ui.theme.Gray500
import com.everymeal.presentation.ui.theme.Gray600
import com.everymeal.presentation.ui.theme.Gray800
import com.everymeal.presentation.ui.theme.Paddings

data class Item(
    val univName: String,
    val campusName: String?
)

@Composable
fun UnivSelectScreen(
    viewModel: UnivSelectViewModel = hiltViewModel(),
    onUnivSelectClick : () -> Unit,
) {
    val viewState by viewModel.viewState.collectAsState()

    val items = listOf(
        Item(univName = "명지대", campusName = "자연캠퍼스"),
        Item(univName = "명지대", campusName = "인문캠퍼스"),
        Item(univName = "성신여대", campusName = "수정캠퍼스"),
        Item(univName = "성신여대", campusName = "운정캠퍼스"),
        Item(univName = "서울여대", campusName = null),
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
            Spacer(modifier = Modifier.padding(40.dp))
            Image(
                painter = painterResource(id = R.drawable.icon_school),
                contentDescription = stringResource(R.string.icon_univ),
                modifier = Modifier.size(64.dp)
            )
            Spacer(modifier = Modifier.padding(10.dp))
            Text(
                text = stringResource(R.string.univ_select_title),
                style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                ),
            )
            Spacer(modifier = Modifier.padding(10.dp))
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.weight(1f),
            ) {
                items(items.size) { index ->
                    val item = items[index]
                    val isSelected = viewState.selectedUniv == "${item.univName}+${item.campusName}"
                    UnivSelectItem(
                        item = item,
                        isSelected = isSelected,
                    ) {
                        viewModel.setEvent(UnivSelectContract.UnivSelectEvent.SelectedUniv(
                            "${item.univName}+${item.campusName}")
                        )
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
                enabled = viewState.selectedUniv.isNotEmpty(),
            ) {
                viewModel.setEvent(UnivSelectContract.UnivSelectEvent.SelectButtonClicked)
            }
        }
    }

    LaunchedEffect(key1 = viewModel.effect) {
        viewModel.effect.collect { effect ->
            when(effect) {
                UnivSelectContract.UnivSelectEffect.MoveToMain -> {
                    onUnivSelectClick()
                }
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
            .padding(bottom = Paddings.medium, end = Paddings.medium)
            .clip(RoundedCornerShape(Paddings.medium))
            .background(if (isSelected) Gray500 else Gray100)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if(item.campusName == null) {
            Spacer(modifier = Modifier.padding(16.dp))
        } else {
            Spacer(modifier = Modifier.padding(10.dp))
        }

        Text(
            text = item.univName,
            fontSize = 13.sp,
            fontWeight = FontWeight.Bold,
            color = Gray800
        )

        if(!item.campusName.isNullOrEmpty()) {
            Spacer(modifier = Modifier.padding(4.dp))
            Text(
                text = item.campusName,
                fontSize = 13.sp,
                color = Gray600,
            )
        }

        if(item.campusName == null) {
            Spacer(modifier = Modifier.padding(16.dp))
        } else {
            Spacer(modifier = Modifier.padding(10.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UnivSelectScreenPreview() {
    EveryMeal_AndroidTheme {
        UnivSelectScreen {

        }
    }
}

@Preview(showBackground = true)
@Composable
fun UnivSelectScreenItemPreview() {
    EveryMeal_AndroidTheme {
        UnivSelectItem(item = Item(
                univName = "명지대",
                campusName = "용인캠퍼스"
            ),
            false
        ) {

        }
    }
}