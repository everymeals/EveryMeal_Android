package com.everymeal.presentation.ui.signup.school

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.everymeal.presentation.R
import com.everymeal.presentation.components.EveryMealConditionAgreeDialog
import com.everymeal.presentation.components.EveryMealConditionAgreeDialogItem
import com.everymeal.presentation.ui.signup.school.email.SchoolAuthEmailVerifyScreen
import com.everymeal.presentation.ui.signup.school.email.SchoolAuthPostEmailScreen
import com.everymeal.presentation.ui.theme.EveryMealTypography

enum class SchoolAuthScreenType {
    POST_EMAIL,
    VERIFY_TOKEN,
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SchoolAuthScreen(
    viewModel: SchoolAuthViewModel = hiltViewModel(),
    onSuccessEmailVerification: (String, String) -> Unit,
) {
    val viewState by viewModel.viewState.collectAsState()
    val context = LocalContext.current
    LaunchedEffect(key1 = viewModel.effect) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is SchoolContract.Effect.Error -> {
                    Log.e(
                        "SchoolAuthScreen",
                        "code: ${effect.code.toString()} message: ${effect.message}"
                    )
                    Toast.makeText(context, "다시 시도해 주세요", Toast.LENGTH_SHORT).show()
                }

                is SchoolContract.Effect.SuccessEmailVerification -> {
                    onSuccessEmailVerification(effect.emailAuthValue, effect.emailAuthToken)
                }
            }
        }
    }
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
                .padding(horizontal = 20.dp),
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
                isAgreed = false,
                isEssential = true,
            ),
            EveryMealConditionAgreeDialogItem(
                title = "[필수] 개인정보 수집 및 이용 동의",
                isAgreed = false,
                isEssential = true,
            ),
            EveryMealConditionAgreeDialogItem(
                title = "[선택] 마케팅 정보 수집 동의",
                isAgreed = false,
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
                Log.d(
                    "TAG",
                    "EmailAuthBottomSheet: ${
                        conditionItems.filter { it.isEssential }.any { it.isAgreed }
                    }"
                )
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
    when (state.schoolAuthScreenType) {
        SchoolAuthScreenType.POST_EMAIL -> {
            SchoolAuthPostEmailScreen(
                modifier = modifier,
                viewModel = viewModel,
                state = state,
            )
        }

        SchoolAuthScreenType.VERIFY_TOKEN -> {
            SchoolAuthEmailVerifyScreen(
                modifier = modifier,
                state = state,
                viewModel = viewModel
            )
        }
    }

}
