@file:OptIn(ExperimentalMaterial3Api::class)

package com.everymeal.presentation.ui.profile

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.everymeal.presentation.R
import com.everymeal.presentation.components.EveryMealMainButton
import com.everymeal.presentation.components.EveryMealTextField
import com.everymeal.presentation.ui.theme.EveryMealTypography
import com.everymeal.presentation.util.noRippleClickable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileGenerateScreen(
    navController: NavController,
    viewModel: ProfileGenerateViewModel = hiltViewModel()
) {
    val viewState by viewModel.viewState.collectAsState()
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.profile_generate_title),
                        style = EveryMealTypography.Subtitle2,
                    )
                },
                navigationIcon = {
                    Image(
                        modifier = Modifier
                            .size(48.dp)
                            .padding(12.dp)
                            .noRippleClickable {
                                navController.popBackStack()
                            },
                        painter = painterResource(id = R.drawable.icon_x_mono),
                        contentDescription = "close"
                    )
                })
        }) { innerPadding ->
        ProfileGenerateContent(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 20.dp),
            viewState = viewState,
            onNicknameChanged = { viewModel.setEvent(ProfileGenerateEvent.OnNicknameChanged(it)) },
            onCompleteButtonClicked = {
                viewModel.setEvent(ProfileGenerateEvent.OnCompleteButtonClicked)
            },
            onProfileImageChanged = {
                viewModel.setEvent(ProfileGenerateEvent.ShowProfileChangeBottomSheet)
            },
            onBottomSheetDismiss = {
                viewModel.setEvent(ProfileGenerateEvent.HideProfileChangeBottomSheet)
            }
        )
    }
}

@Composable
fun ProfileGenerateContent(
    modifier: Modifier = Modifier,
    viewState: ProfileGenerateState,
    onNicknameChanged: (String) -> Unit = {},
    onCompleteButtonClicked: () -> Unit = {},
    onProfileImageChanged: () -> Unit = {},
    onBottomSheetDismiss: () -> Unit = {},
    onProfileChangeCompleteButtonClicked: () -> Unit = {},
) {

    if (viewState.isShowProfileChangeBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = { onBottomSheetDismiss() },
            containerColor = Color.White,
        ) {
            Column(
                modifier = Modifier
                    .padding(20.dp)
            ) {
                Text(text = "이미지 선택")
                Spacer(modifier = Modifier.height(20.dp))
                Image(
                    modifier = Modifier
                        .size(90.dp)
                        .align(Alignment.CenterHorizontally),
                    painter = painterResource(id = R.drawable.rice_90),
                    contentDescription = null
                )
                Spacer(modifier = Modifier.height(32.dp))
                val buttonList = listOf(
                    painterResource(id = R.drawable.ic_btn_camera),
                    painterResource(id = R.drawable.rice_90),
                    painterResource(id = R.drawable.sushi_90),
                    painterResource(id = R.drawable.puding_90),
                    painterResource(id = R.drawable.ic_btn_picture),
                    painterResource(id = R.drawable.apple_90),
                    painterResource(id = R.drawable.egg_90),
                    painterResource(id = R.drawable.ramen_90),
                )
                LazyVerticalGrid(
                    modifier = Modifier.padding(horizontal = 38.dp),
                    columns = GridCells.Fixed(4),
                    verticalArrangement = Arrangement.spacedBy(14.dp),
                    horizontalArrangement = Arrangement.spacedBy(20.dp),
                    userScrollEnabled = false,
                ) {
                    itemsIndexed(buttonList) { index, painter ->
                        Image(
                            modifier = Modifier.size(50.dp),
                            painter = painter,
                            contentDescription = null
                        )
                    }
                }
                EveryMealMainButton(text = stringResource(id = R.string.complete)) {
                    onProfileChangeCompleteButtonClicked()
                }
                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(32.dp))
        Box(
            modifier = Modifier.noRippleClickable(onClick = onProfileImageChanged)
        ) {
            Image(
                modifier = Modifier
                    .size(90.dp)
                    .align(Alignment.Center),
                painter = painterResource(id = R.drawable.rice_90),
                contentDescription = null
            )
            Image(
                modifier = Modifier
                    .size(28.dp)
                    .align(Alignment.BottomEnd),
                painter = painterResource(id = R.drawable.img_add_gray),
                contentDescription = null
            )
        }
        Spacer(modifier = Modifier.height(40.dp))
        Text(
            modifier = Modifier.align(Alignment.Start),
            text = stringResource(id = R.string.nickname),
            style = EveryMealTypography.Body5,
        )
        Spacer(modifier = Modifier.height(6.dp))
        EveryMealTextField(
            modifier = Modifier.fillMaxWidth(),
            value = viewState.nickname,
            onValueChange = onNicknameChanged,
            placeholderText = stringResource(id = R.string.nickname_rule),
            maxLines = 1
        )
        Spacer(modifier = Modifier.weight(1f))
        EveryMealMainButton(
            text = stringResource(id = R.string.complete),
            onClick = {
                onCompleteButtonClicked()
            },
            enabled = viewState.isNicknameValid
        )
    }
}
