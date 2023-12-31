package com.everymeal.presentation.ui.signup

import android.annotation.SuppressLint
import android.util.Log
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
import androidx.compose.runtime.mutableStateOf
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
import com.everymeal.domain.model.onboarding.GetUniversityEntity
import com.everymeal.presentation.R
import com.everymeal.presentation.base.LoadState
import com.everymeal.presentation.components.EveryMealDialog
import com.everymeal.presentation.components.EveryMealLoadingDialog
import com.everymeal.presentation.components.EveryMealMainButton
import com.everymeal.presentation.ui.onboarding.OnboardingActivity
import com.everymeal.presentation.ui.theme.EveryMeal_AndroidTheme
import com.everymeal.presentation.ui.theme.Gray100
import com.everymeal.presentation.ui.theme.Gray300
import com.everymeal.presentation.ui.theme.Gray500
import com.everymeal.presentation.ui.theme.Gray600
import com.everymeal.presentation.ui.theme.Gray800
import com.everymeal.presentation.ui.theme.Paddings

@Composable
fun UnivSelectScreen(
    viewModel: UnivSelectViewModel = hiltViewModel(),
    onUnivSelectClick : () -> Unit,
    onNetWorkErrorCancelClick : () -> Unit
) {
    val viewState by viewModel.viewState.collectAsState()

    LaunchedEffect(key1 = true) {
        viewModel.setEvent(UnivSelectContract.UnivSelectEvent.InitUnivSelectScreen)
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

    when(viewState.univSelectLoadState) {
        LoadState.LOADING -> {
            EveryMealLoadingDialog()
        }
        LoadState.SUCCESS -> {
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
                        items(viewState.universities.size) { index ->
                            val item = viewState.universities[index]
                            val isSelected = viewState.selectedUniv == "${item.universityShortName}+${item.campusName}"
                            UnivSelectItem(
                                item = item,
                                isSelected = isSelected,
                                index = index
                            ) {
                                viewModel.setEvent(UnivSelectContract.UnivSelectEvent.SelectedUniv(
                                    selectedUniv = "${item.universityShortName}+${item.campusName}",
                                    univIdx = item.idx,
                                    univSelectFullName = item.universityName,
                                    campusName = item.campusName)
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
                        viewModel.setEvent(UnivSelectContract.UnivSelectEvent.SelectButtonClicked(
                            univIdx = viewState.univIdx,
                            univSelectFullName = viewState.univSelectFullName,
                            campusName = viewState.campusName
                        ))
                    }
                }
            }
        }
        LoadState.ERROR -> {
            if(viewState.networkErrorDialog) {
                EveryMealDialog(
                    modifier = Modifier.fillMaxWidth(),
                    title = stringResource(R.string.error_dialog_title),
                    message = stringResource(R.string.error_dialog_content),
                    confirmButtonText = stringResource(R.string.retry),
                    dismissButtonText = stringResource(R.string.cancel),
                    onDismissRequest = { },
                    onConfirmClick = {
                        viewModel.setEvent(UnivSelectContract.UnivSelectEvent.NetworkErrorDialogClicked(false))
                        viewModel.setEvent(UnivSelectContract.UnivSelectEvent.InitUnivSelectScreen)
                        viewModel.setEvent(UnivSelectContract.UnivSelectEvent.NetworkErrorDialogClicked(true))
                    },
                    onDisMissClicked = {
                        viewModel.setEvent(UnivSelectContract.UnivSelectEvent.NetworkErrorDialogClicked(false))
                        onNetWorkErrorCancelClick()
                    }
                )
            }
        }
    }
}

@SuppressLint("RememberReturnType")
@Composable
fun UnivSelectItem(
    item: GetUniversityEntity.UniversityData,
    isSelected: Boolean,
    index: Int,
    onSelectClick: (GetUniversityEntity.UniversityData) -> Unit
) {
    Column(
        modifier = Modifier
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) { onSelectClick(item) }
            .padding(bottom = Paddings.medium, end = if (index % 2 == 0) Paddings.medium else 0.dp)
            .clip(RoundedCornerShape(Paddings.medium))
            .background(if (isSelected) Gray500 else Gray100)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        val spacerSize = if (item.campusName.isEmpty()) 17.dp else 10.dp
        Spacer(modifier = Modifier.padding(spacerSize))
        Text(
            text = item.universityShortName,
            fontSize = 13.sp,
            fontWeight = FontWeight.Bold,
            color = Gray800
        )
        if (item.campusName.isNotEmpty()) {
            Spacer(modifier = Modifier.padding(4.dp))
            Text(
                text = item.campusName,
                fontSize = 13.sp,
                color = Gray600,
            )
        }
        Spacer(modifier = Modifier.padding(spacerSize))
    }
}

@Preview(showBackground = true)
@Composable
fun UnivSelectScreenPreview() {
    EveryMeal_AndroidTheme {
        UnivSelectScreen(
            onUnivSelectClick = { },
            onNetWorkErrorCancelClick = { }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun UnivSelectScreenItemPreview() {
    EveryMeal_AndroidTheme {

    }
}