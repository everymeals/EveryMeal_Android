package com.everymeal.presentation.ui.signup.school

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.everymeal.presentation.R
import com.everymeal.presentation.components.EveryMealConditionAgreeDialog
import com.everymeal.presentation.components.EveryMealConditionAgreeDialogItem
import com.everymeal.presentation.components.EveryMealMainButton
import com.everymeal.presentation.components.EveryMealTextField
import com.everymeal.presentation.ui.theme.EveryMealTypography
import com.everymeal.presentation.ui.theme.Main100

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SchoolAuthScreen(
    viewModel: SchoolAuthViewModel = hiltViewModel()
) {
    val viewState by viewModel.viewState.collectAsState()
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.school_auth_title),
                        style = EveryMealTypography.Subtitle2,
                    )
                },
                navigationIcon = {
                    Image(
                        modifier = Modifier
                            .size(48.dp)
                            .padding(12.dp),
                        painter = painterResource(id = R.drawable.icon_x_mono),
                        contentDescription = "close"
                    )
                })
        }
    )
    { innerPadding ->
        SchoolAuthContent(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 20.dp)
                .padding(top = 28.dp),
            viewModel = viewModel,
            state = viewState
        )
        if (viewState.isShowConditionBottomSheet) {
            EmailAuthBottomSheet(viewModel)
        }
    }
}

@Composable
private fun EmailAuthBottomSheet(viewModel: SchoolAuthViewModel) {
    val conditionItems = remember {
        mutableStateListOf(
            EveryMealConditionAgreeDialogItem(
                title = "[필수] 이용 약관 동의",
                isAgreed = true,
                isEssential = true,
            ),
            EveryMealConditionAgreeDialogItem(
                title = "[필수] 개인정보 수집 및 이용 동의",
                isAgreed = true,
                isEssential = true,
            ),
            EveryMealConditionAgreeDialogItem(
                title = "[선택] 마케팅 정보 수집 동의",
                isAgreed = true,
            )
        )
    }

    EveryMealConditionAgreeDialog(
        onItemClicked = {
            conditionItems[it] =
                conditionItems[it].copy(isAgreed = !conditionItems[it].isAgreed)
        },
        onNextButtonClicked = {
            if (conditionItems.filter { it.isEssential }.any { it.isAgreed }) {
                viewModel.setEvent(SchoolContract.Event.OnPostEmail)
            }
        },
        onDismiss = {},
        conditionItems = conditionItems
    )
}

@Composable
fun SchoolAuthContent(
    modifier: Modifier = Modifier,
    viewModel: SchoolAuthViewModel,
    state: SchoolContract.State
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = stringResource(id = R.string.school_auth_content),
            style = EveryMealTypography.Heading1
        )
        Spacer(modifier = Modifier.size(40.dp))
        Text(
            text = stringResource(id = R.string.email),
            style = EveryMealTypography.Body5
        )
        Spacer(modifier = Modifier.size(6.dp))
        EveryMealTextField(
            modifier = Modifier.fillMaxWidth(),
            value = state.emailLink,
            onValueChange = {
                viewModel.setEvent(SchoolContract.Event.OnEmailTextChanged(it))
            },
            supportingText = {
                if (state.isEmailError) {
                    Text(
                        text = stringResource(id = R.string.email_error),
                        style = EveryMealTypography.Body5,
                        color = Main100
                    )
                }
            }
        )
        Spacer(modifier = Modifier.weight(1f))
        EveryMealMainButton(
            text = stringResource(id = R.string.next),
            onClick = {
                viewModel.setEvent(SchoolContract.Event.OnNextButtonClicked)
            },
        )
    }
}


@Preview
@Composable
fun SchoolAuthScreenPreview() {
    SchoolAuthScreen()
}
