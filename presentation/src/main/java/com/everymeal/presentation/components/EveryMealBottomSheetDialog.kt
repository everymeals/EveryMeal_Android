package com.everymeal.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.everymeal.presentation.R
import com.everymeal.presentation.ui.theme.Gray600
import com.everymeal.presentation.ui.theme.Gray900

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EveryMealMainBottomSheetDialog(
    title: String,
    content: String,
    onClick : () -> Unit,
    onDismiss: () -> Unit
) {
    ModalBottomSheet(
        onDismissRequest = { onDismiss() },
        containerColor = Color.White,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.icon_school),
                contentDescription = stringResource(R.string.icon_univ),
                modifier = Modifier.size(64.dp)
            )
            Spacer(modifier = Modifier.padding(10.dp))
            Text(
                text = title,
                fontSize = 22.sp,
                color = Gray900,
                fontWeight = FontWeight.Bold,
            )
            Spacer(modifier = Modifier.padding(4.dp))
            Text(
                text = content,
                fontSize = 15.sp,
                color = Gray600,
                fontWeight = FontWeight.Medium,
            )
            Spacer(modifier = Modifier.padding(10.dp))
            EveryMealMainButton(
                text = stringResource(R.string.univ_admin_button),
                onClick = onClick,
            )
            Spacer(modifier = Modifier.padding(10.dp))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EveryMealSortCategoryBottomSheetDialog(
    onClick: (String) -> Unit,
    onDismiss: () -> Unit
) {
    ModalBottomSheet(
        onDismissRequest = { onDismiss() },
        containerColor = Color.White,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onClick("인기순") }
                    .padding(vertical = 14.dp),
                text = stringResource(R.string.popularity_sort),
                fontSize = 17.sp,
                color = Gray900,
                fontWeight = FontWeight.SemiBold,
            )
            Spacer(modifier = Modifier.padding(4.dp))
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onClick("거리순") }
                    .padding(vertical = 14.dp),
                text = stringResource(R.string.distance_sort),
                fontSize = 17.sp,
                color = Gray900,
                fontWeight = FontWeight.SemiBold,
            )
            Spacer(modifier = Modifier.padding(4.dp))
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onClick("최신순") }
                    .padding(vertical = 14.dp),
                text = stringResource(R.string.recent_sort),
                fontSize = 17.sp,
                color = Gray900,
                fontWeight = FontWeight.SemiBold,
            )
            Spacer(modifier = Modifier.padding(10.dp))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EveryMealCategoryRatingBottomSheetDialog(
    onClick: () -> Unit,
    onDismiss: () -> Unit
) {
    ModalBottomSheet(
        onDismissRequest = { onDismiss() },
        containerColor = Color.White,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 14.dp),
                text = stringResource(R.string.meal_category),
                fontSize = 17.sp,
                color = Gray900,
                fontWeight = FontWeight.SemiBold,
            )
            Spacer(modifier = Modifier.padding(4.dp))
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 14.dp),
                text = stringResource(R.string.rating_category),
                fontSize = 17.sp,
                color = Gray900,
                fontWeight = FontWeight.SemiBold,
            )
            Spacer(modifier = Modifier.padding(4.dp))
            EveryMealMainButton(
                text = stringResource(R.string.meal_rating_category_apply),
                onClick = onClick,
            )
            Spacer(modifier = Modifier.padding(10.dp))
        }
    }
}