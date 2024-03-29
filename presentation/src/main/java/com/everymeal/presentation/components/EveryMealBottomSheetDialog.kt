package com.everymeal.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.everymeal.presentation.R
import com.everymeal.presentation.ui.detail.ReportCategoryType
import com.everymeal.presentation.ui.detail.RestaurantCategoryType
import com.everymeal.presentation.ui.home.HomeCategoryList
import com.everymeal.presentation.ui.theme.EveryMealTypo
import com.everymeal.presentation.ui.theme.EveryMealTypography
import com.everymeal.presentation.ui.theme.Gray200
import com.everymeal.presentation.ui.theme.Gray400
import com.everymeal.presentation.ui.theme.Gray600
import com.everymeal.presentation.ui.theme.Gray700
import com.everymeal.presentation.ui.theme.Gray800
import com.everymeal.presentation.ui.theme.Gray900
import com.everymeal.presentation.ui.theme.Grey2
import com.everymeal.presentation.ui.theme.Main100
import com.everymeal.presentation.ui.theme.SubMain100
import com.everymeal.presentation.ui.theme.Typography
import com.google.accompanist.insets.LocalWindowInsets
import com.google.accompanist.insets.rememberInsetsPaddingValues

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EveryMealMainBottomSheetDialog(
    title: String,
    content: String,
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
            Spacer(modifier = Modifier.padding(24.dp))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EveryMealSortCategoryBottomSheetDialog(
    title: String,
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
            SortCategoryItem(
                title = title,
                category = stringResource(R.string.popularity_sort),
                onClick = onClick
            )
            Spacer(modifier = Modifier.padding(4.dp))
            SortCategoryItem(
                title = title,
                category = stringResource(R.string.distance_sort),
                onClick = onClick
            )
            Spacer(modifier = Modifier.padding(4.dp))
            SortCategoryItem(
                title = title,
                category = stringResource(R.string.recent_sort),
                onClick = onClick
            )
            Spacer(modifier = Modifier.padding(10.dp))
        }
    }
}

@Composable
fun SortCategoryItem(
    title: String,
    category: String,
    onClick: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick(category) },
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.padding(vertical = 14.dp),
            text = category,
            fontSize = 17.sp,
            color = Gray900,
            style = EveryMealTypo.displayMedium,
        )
        if (title == category) {
            Image(
                modifier = Modifier.size(24.dp),
                imageVector = ImageVector.vectorResource(R.drawable.icon_check_mono),
                contentDescription = null,
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EveryMealCategoryRatingBottomSheetDialog(
    title: String = "",
    currentRating: Int,
    restaurantCategoryType: String,
    onClick: () -> Unit,
    onDismiss: () -> Unit,
    onCategoryClick: (String) -> Unit,
    onRatingClick: (Int) -> Unit
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
            if (title.RestaurantCategoryType() != RestaurantCategoryType.CAFE
                && title.RestaurantCategoryType() != RestaurantCategoryType.DRINK
            ) {
                Spacer(modifier = Modifier.padding(4.dp))
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
                HomeCategoryList(
                    isBottomSheet = true,
                    restaurantCategoryType
                ) {
                    onCategoryClick(it)
                }
                Spacer(modifier = Modifier.padding(14.dp))
                Divider(
                    color = Gray200,
                )
            }
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
            LazyRow(content = {
                items(5) {
                    RatingItem(
                        currentRating,
                        ratingCount = it + 1,
                        onRatingClick = onRatingClick
                    )
                    Spacer(modifier = Modifier.padding(4.dp))
                }
            })
            Spacer(modifier = Modifier.padding(4.dp))
            EveryMealMainButton(
                text = stringResource(R.string.meal_rating_category_apply),
                onClick = onClick,
            )
            Spacer(modifier = Modifier.padding(10.dp))
        }
    }
}

@Composable
fun RatingItem(
    currentRating: Int,
    ratingCount: Int,
    onRatingClick: (Int) -> Unit
) {
    Surface(
        modifier = Modifier.clickable(
            indication = null,
            interactionSource = remember { MutableInteractionSource() }
        ) { onRatingClick(ratingCount) },
        color = if (currentRating == ratingCount) SubMain100 else Grey2,
        shape = RoundedCornerShape(100.dp),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier
                    .padding(start = 12.dp)
                    .size(16.dp),
                imageVector = ImageVector.vectorResource(R.drawable.icon_gray_star_mono),
                contentDescription = "rating",
                colorFilter = if (currentRating == ratingCount) {
                    ColorFilter.tint(Main100)
                } else {
                    ColorFilter.tint(Gray600)
                }
            )
            Text(
                text = ratingCount.toString(),
                color = if (currentRating == ratingCount) Main100 else Gray600,
                style = Typography.bodySmall,
                modifier = Modifier.padding(start = 4.dp, end = 12.dp, top = 6.dp, bottom = 6.dp)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EveryMealReportBottomSheetDialog(
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
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onClick() },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier.size(24.dp),
                    imageVector = ImageVector.vectorResource(R.drawable.icon_siren_mono),
                    contentDescription = null,
                )
                Text(
                    modifier = Modifier.padding(start = 12.dp),
                    text = stringResource(R.string.report),
                    fontSize = 17.sp,
                    color = Gray900,
                    fontWeight = FontWeight.SemiBold,
                )
            }
            Spacer(modifier = Modifier.padding(20.dp))
        }
    }
}

data class EveryMealConditionAgreeDialogItem(
    val title: String,
    val isAgreed: Boolean,
    val isEssential: Boolean = false,
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EveryMealConditionAgreeDialog(
    onItemClicked: (Int) -> Unit,
    onNextButtonClicked: () -> Unit,
    onDismiss: () -> Unit,
    conditionItems: List<EveryMealConditionAgreeDialogItem>
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
                text = stringResource(R.string.condition_agree_title),
                style = EveryMealTypography.Heading2,
                color = Gray900,
            )
            Spacer(modifier = Modifier.size(13.dp))
            LazyColumn(content = {
                itemsIndexed(conditionItems) { index, item ->
                    Row(
                        modifier = Modifier
                            .clickable(onClick = {
                                onItemClicked(index)
                            })
                            .padding(vertical = 7.dp),
                    ) {
                        Image(
                            painter = if (item.isAgreed) painterResource(
                                id = R.drawable.icon_check_mono
                            ) else painterResource(id = R.drawable.icon_check_gray_mono),
                            contentDescription = "check"
                        )
                        Text(
                            modifier = Modifier.padding(start = 6.dp),
                            text = item.title,
                            style = EveryMealTypography.Subtitle3,
                            color = Gray700,
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        Image(
                            painter = painterResource(id = R.drawable.icon_arrow_right),
                            contentDescription = null
                        )
                    }
                }
            })
            EveryMealMainButton(
                text = stringResource(R.string.ok),
                enabled = true,
                onClick = onNextButtonClicked,
            )
            Spacer(modifier = Modifier.size(24.dp))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EveryMealDetailReportBottomSheetDialog(
    title: String,
    onClick: () -> Unit,
    onDismiss: () -> Unit,
    onReportCategoryClick: (String) -> Unit
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
                modifier = Modifier.padding(bottom = 18.dp),
                text = stringResource(R.string.select_what_report),
                fontSize = 22.sp,
                style = EveryMealTypo.titleMedium,
                color = Gray900
            )
            ReportCategoryItem(
                title = title,
                category = stringResource(R.string.restaurant_not_review),
                onClick = onReportCategoryClick
            )
            Spacer(modifier = Modifier.padding(4.dp))
            ReportCategoryItem(
                title = title,
                category = stringResource(R.string.dangerous_speak_review),
                onClick = onReportCategoryClick
            )
            Spacer(modifier = Modifier.padding(4.dp))
            ReportCategoryItem(
                title = title,
                category = stringResource(R.string.lustful_review),
                onClick = onReportCategoryClick
            )
            Spacer(modifier = Modifier.padding(4.dp))
            EveryMealMainButton(
                text = stringResource(R.string.ok),
                enabled = title.ReportCategoryType() != ReportCategoryType.NONE,
                onClick = onClick,
            )
            Spacer(modifier = Modifier.padding(10.dp))
        }
    }
}

@Composable
fun ReportCategoryItem(
    title: String,
    category: String,
    onClick: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) { onClick(category) },
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            modifier = Modifier.padding(vertical = 10.dp),
            text = category,
            fontSize = 16.sp,
            color = Gray800,
            fontWeight = FontWeight.Normal,
            style = EveryMealTypo.bodySmall
        )
        Image(
            modifier = Modifier.size(24.dp),
            imageVector = ImageVector.vectorResource(R.drawable.icon_check_gray_mono),
            contentDescription = null,
            colorFilter = if (title == category) {
                ColorFilter.tint(Main100)
            } else {
                ColorFilter.tint(Gray400)
            }
        )
    }
}
