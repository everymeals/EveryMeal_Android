package com.everymeal.presentation.ui.withdraw

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.everymeal.presentation.R
import com.everymeal.presentation.ui.save.SaveTopBar
import com.everymeal.presentation.ui.theme.EveryMealTypography
import com.everymeal.presentation.ui.theme.Gray100
import com.everymeal.presentation.ui.theme.Gray300
import com.everymeal.presentation.ui.theme.Gray400
import com.everymeal.presentation.ui.theme.Gray700
import com.everymeal.presentation.ui.theme.Gray800
import com.everymeal.presentation.ui.theme.Gray900
import com.everymeal.presentation.ui.theme.Main100
import com.everymeal.presentation.util.noRippleClickable

@Composable
fun WithDrawScreen(
    viewModel: WithDrawViewModel = hiltViewModel(),
    onBackClick: () -> Unit = {}
) {
    val viewState by viewModel.viewState.collectAsState()

    Scaffold(
        topBar = {
            SaveTopBar(
                title = "탈퇴하기",
                onBackClick = onBackClick
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(horizontal = 20.dp)
        ) {
            item {
                Spacer(modifier = Modifier.padding(top = 40.dp))
                Text(
                    text = "에브리밀을 정말 떠나시겠어요?",
                    style = EveryMealTypography.Heading1,
                    color = Gray900
                )
                Spacer(modifier = Modifier.padding(top = 6.dp))
                Text(
                    text = "탈퇴 이유를 알려주시면 큰 도움이 돼요",
                    style = EveryMealTypography.Body2,
                    color = Gray700
                )
            }

            item {
                Spacer(modifier = Modifier.padding(top = 36.dp))
                WithDrawReason(viewModel, viewState, WithDrawReasonType.NOT_USE)
                WithDrawReason(viewModel, viewState, WithDrawReasonType.INCONVENIENT)
                WithDrawReason(viewModel, viewState, WithDrawReasonType.ERROR)
                WithDrawReason(viewModel, viewState, WithDrawReasonType.CHANGE_SCHOOL)
                WithDrawReason(viewModel, viewState, WithDrawReasonType.ETC)
            }
        }
    }
}

@Composable
fun WithDrawReason(
    viewModel : WithDrawViewModel = hiltViewModel(),
    viewState : WithDrawContract.WithDrawState,
    withDrawReasonType : WithDrawReasonType,
) {
    val isSelected = viewState.selectedReason == withDrawReasonType

    Row(
        modifier = Modifier
            .border(1.dp, if(isSelected) Main100 else Gray300, RoundedCornerShape(10.dp))
            .fillMaxWidth()
            .noRippleClickable {
                viewModel.setEvent(WithDrawContract.WithDrawEvent.WithDrawReasonSelected(withDrawReasonType))
            }
            .padding(vertical = 18.dp, horizontal = 16.dp)
    ) {
        Text(
            text = withDrawReasonType.reason,
            style = EveryMealTypography.Subtitle3,
            color = Gray800
        )
        Spacer(modifier = Modifier.weight(1f))
        Image(
            imageVector = ImageVector.vectorResource(id = R.drawable.icon_check_circle_mono),
            contentDescription = null,
            colorFilter = if(isSelected) ColorFilter.tint(Main100) else ColorFilter.tint(Gray400),
        )
    }
    Spacer(modifier = Modifier.padding(bottom = 12.dp))
}

@Preview
@Composable
fun WithDrawScreenPreview() {
    WithDrawScreen()
}