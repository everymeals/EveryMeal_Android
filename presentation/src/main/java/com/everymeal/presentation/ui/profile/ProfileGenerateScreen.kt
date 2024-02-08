package com.everymeal.presentation.ui.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.everymeal.presentation.R
import com.everymeal.presentation.components.EveryMealTextField
import com.everymeal.presentation.ui.theme.EveryMealTypography
import com.everymeal.presentation.util.noRippleClickable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileGenerateScreen(navController: NavController) {
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
                .padding(innerPadding)
                .padding(horizontal = 20.dp),
        )
    }
}

@Composable
fun ProfileGenerateContent(
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Image(
            modifier = Modifier.size(90.dp),
            painter = painterResource(id = R.drawable.rice_90),
            contentDescription = null
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(text = stringResource(id = R.string.nickname))
        Spacer(modifier = Modifier.height(12.dp))
        EveryMealTextField(
            modifier = Modifier.padding(top = 8.dp),
            value = "",
            onValueChange = {},
            placeholderText = stringResource(id = R.string.nickname_rule),
        )

    }
}
