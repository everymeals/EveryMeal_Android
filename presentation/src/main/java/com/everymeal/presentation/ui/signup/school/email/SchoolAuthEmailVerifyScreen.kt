package com.everymeal.presentation.ui.signup.school.email

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.everymeal.presentation.R
import com.everymeal.presentation.components.EveryMealMainButton
import com.everymeal.presentation.components.EveryMealTextField
import com.everymeal.presentation.ui.signup.school.SchoolAuthViewModel
import com.everymeal.presentation.ui.signup.school.SchoolContract
import com.everymeal.presentation.ui.theme.EveryMealTypography
import com.everymeal.presentation.ui.theme.Gray100
import com.everymeal.presentation.ui.theme.Gray900

@Composable
fun SchoolAuthEmailVerifyScreen(
    modifier: Modifier,
    state: SchoolContract.State,
    viewModel: SchoolAuthViewModel
) {
    Column(
        modifier = modifier.padding(top = 48.dp)
    ) {
        Text(
            text = stringResource(id = R.string.email_token_verify_title),
            style = EveryMealTypography.Heading1,
            color = Gray900
        )
        Spacer(modifier = Modifier.size(40.dp))
        Text(
            text = stringResource(id = R.string.verify_token),
            style = EveryMealTypography.Body5,
            color = Gray100
        )
        Spacer(modifier = Modifier.size(6.dp))
        EveryMealTextField(
            modifier = Modifier.fillMaxWidth(),
            value = state.emailAuthValue,
            onValueChange = {
                viewModel.setEvent(SchoolContract.Event.OnTokenTextChanged(it))
            },
        )
        Spacer(modifier = Modifier.weight(1f))
        EveryMealMainButton(
            text = stringResource(id = R.string.next),
            onClick = {
                viewModel.setEvent(SchoolContract.Event.OnTokenNextButtonClicked)
            },
        )
    }
}
