package com.everymeal.presentation.ui.mypage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.everymeal.presentation.R
import com.everymeal.presentation.ui.theme.EveryMealTypography
import com.everymeal.presentation.ui.theme.Gray100
import com.everymeal.presentation.ui.theme.Gray200
import com.everymeal.presentation.ui.theme.Gray400
import com.everymeal.presentation.ui.theme.Gray600
import com.everymeal.presentation.ui.theme.Gray800
import com.everymeal.presentation.ui.theme.Gray900
import com.everymeal.presentation.util.noRippleClickable


@Composable
fun MyPageScreen(
    viewModel : MyPageViewModel = hiltViewModel(),
    withDrawClick : () -> Unit = {}
) {
    Scaffold { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .background(Color.White)
                .padding(top = 20.dp),
        ) {
            item(key = "My Information") {
                Spacer(modifier = Modifier.padding(8.dp))
                MyInformation(Modifier.padding(horizontal = 20.dp))
                Divider(
                    modifier = Modifier.padding(20.dp),
                    color = Gray200,
                    thickness = 1.dp
                )
            }

            item(key = "My Activities") {
                MyActivities(Modifier.padding(horizontal = 20.dp)) {

                }
                Spacer(modifier = Modifier.padding(24.dp))
                Divider(
                    color = Gray100,
                    thickness = 12.dp
                )
                Spacer(modifier = Modifier.padding(24.dp))
            }

            item(key = "My Settings") {
                MySettings(Modifier.padding(horizontal = 20.dp)) {
                    when(it) {
                        MyPageSetting.INQUIRY.type -> {
                            // TODO
                        }
                        MyPageSetting.SERVICE_TERMS.type -> {
                            // TODO
                        }
                        MyPageSetting.OPEN_SOURCE_LICENSE.type -> {
                            // TODO
                        }
                        MyPageSetting.VERSION_INFO.type -> {
                            // TODO
                        }
                        MyPageSetting.WITHDRAWAL.type -> {
                            withDrawClick()
                        }
                    }
                }
                Spacer(modifier = Modifier.padding(24.dp))
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
                style = EveryMealTypography.Title1,
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
                    style = EveryMealTypography.Title3,
                    color = Gray900
                )
                Spacer(modifier = Modifier.padding(2.dp))
                Text(
                    text = stringResource(id = R.string.my_page_if_need_can_function),
                    style = EveryMealTypography.Body3,
                    color = Gray600,
                    maxLines = 2
                )
            }
        }
    }
}

@Composable
fun MyActivities(
    modifier: Modifier = Modifier,
    onClick: (String) -> Unit
) {
    Column (
        modifier = modifier
    ) {
        Text(
            text = stringResource(id = R.string.my_page_my_activities),
            style = EveryMealTypography.Title1,
            color = Gray900
        )
        MyTabMenu(
            menuTitle = "저장",
            onClick = { }
        )
        MyTabMenu(
            menuTitle = "리뷰 내역",
            onClick = { }
        )
        MyTabMenu(
            menuTitle = "사진 내역",
            onClick = { }
        )
    }
}

@Composable
fun MySettings(
    modifier: Modifier = Modifier,
    onClick: (String) -> Unit
) {
    Column (
        modifier = modifier
    ) {
        Text(
            text = stringResource(id = R.string.my_page_settings),
            style = EveryMealTypography.Title1,
            color = Gray900
        )
        MyTabMenu(
            menuTitle = MyPageSetting.INQUIRY.type,
            onClick = { onClick(MyPageSetting.INQUIRY.type) }
        )
        MyTabMenu(
            menuTitle = MyPageSetting.SERVICE_TERMS.type,
            onClick = { onClick(MyPageSetting.SERVICE_TERMS.type) }
        )
        MyTabMenu(
            menuTitle = MyPageSetting.OPEN_SOURCE_LICENSE.type,
            onClick = { onClick(MyPageSetting.OPEN_SOURCE_LICENSE.type) }
        )
        MyTabMenu(
            menuTitle = MyPageSetting.VERSION_INFO.type,
            isAppVersion = true,
            onClick = { onClick(MyPageSetting.VERSION_INFO.type) }
        )
        MyTabMenu(
            menuTitle = MyPageSetting.WITHDRAWAL.type,
            onClick = { onClick(MyPageSetting.WITHDRAWAL.type) }
        )
    }
}

@Composable
fun MyTabMenu(
    modifier: Modifier = Modifier,
    menuTitle: String,
    isAppVersion: Boolean = false,
    onClick: () -> Unit,
) {
    Column (
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 24.dp)
            .noRippleClickable(onClick = onClick)
            .padding(vertical = 3.dp)
    ) {
        Row (
            modifier = modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = menuTitle,
                style = EveryMealTypography.Body2,
                color = Gray800
            )
            if(isAppVersion) {
                Text(
                    text = "1.0.0",
                    style = EveryMealTypography.Body2,
                    color = Gray400
                )
            } else {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.icon_arrow_right),
                    contentDescription = null,
                    modifier = Modifier.size(20.dp),
                    tint = Gray400
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