package com.everymeal.presentation.ui.mypage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.everymeal.presentation.R
import com.everymeal.presentation.ui.theme.EveryMealTypography
import com.everymeal.presentation.ui.theme.Gray100
import com.everymeal.presentation.ui.theme.Gray600
import com.everymeal.presentation.ui.theme.Gray900


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyPageScreen(

) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.padding(innerPadding),
        ) {
            item(key = "My Information") {
                Spacer(modifier = Modifier.padding(8.dp))
                MyInformation(Modifier.padding(horizontal = 20.dp))
            }
        }
    }
}

@Composable
fun MyInformation(
    modifier: Modifier = Modifier
) {
    Column (
        modifier = modifier
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                imageVector = ImageVector.vectorResource(id = R.drawable.my_page_bean_image),
                contentDescription = null,
            )
            Text(
                text = stringResource(id = R.string.my_page_univ_correct),
                style = EveryMealTypography.titleLarge,
                modifier = Modifier.padding(start = 16.dp)
            )
            Spacer(Modifier.weight(1f))
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.icon_arrow_right),
                contentDescription = null,
                modifier = Modifier.size(24.dp)
            )
        }
        Spacer(modifier = Modifier.padding(16.dp))
        Row (
            modifier = Modifier
                .background(Gray100, RoundedCornerShape(8.dp))
                .padding(14.dp)
        ) {
            Image(
                modifier = Modifier.size(44.dp),
                painter = painterResource(id = R.drawable.icon_school),
                contentDescription = null
            )
            Spacer(modifier = Modifier.padding(10.dp))
            Column {
                Text(
                    text = stringResource(id = R.string.my_page_univ_need),
                    style = EveryMealTypography.titleSmall,
                    color = Gray900
                )
                Spacer(modifier = Modifier.padding(2.dp))
                Text(
                    text = stringResource(id = R.string.my_page_if_need_can_function),
                    style = EveryMealTypography.bodySmall,
                    color = Gray600,
                    maxLines = 2
                )
            }
        }
    }
}

@Preview
@Composable
fun MyPageScreenPreview() {
    MyPageScreen()
}