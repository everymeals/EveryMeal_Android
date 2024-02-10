package com.everymeal.presentation.ui.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.everymeal.presentation.R
import com.everymeal.presentation.components.EveryMealMainButton
import com.everymeal.presentation.components.EveryMealTextField
import com.everymeal.presentation.ui.signup.school.SchoolContract
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
            }
        )
    }
}

@Composable
fun ProfileGenerateContent(
    modifier: Modifier = Modifier,
    viewState: ProfileGenerateState,
    onNicknameChanged: (String) -> Unit = {},
    onCompleteButtonClicked: () -> Unit = {}
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(32.dp))
        Image(
            modifier = Modifier.size(90.dp),
            painter = painterResource(id = R.drawable.rice_90),
            contentDescription = null
        )
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
