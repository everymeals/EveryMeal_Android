package com.everymeal.presentation.ui.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.everymeal.presentation.R
import com.everymeal.presentation.components.EveryMealCategoryRatingBottomSheetDialog
import com.everymeal.presentation.components.EveryMealDetailReportBottomSheetDialog
import com.everymeal.presentation.components.EveryMealReportBottomSheetDialog
import com.everymeal.presentation.components.EveryMealSortCategoryBottomSheetDialog
import com.everymeal.presentation.ui.save.SaveTopBar
import com.everymeal.presentation.ui.theme.Grey2
import com.everymeal.presentation.ui.theme.Grey7
import com.everymeal.presentation.ui.theme.Typography

@Composable
fun DetailListScreen(
    detailListViewModel: DetailListViewModel = hiltViewModel(),
    title: String,
    navigateToPreviousScreen: () -> Unit,
) {
    val detailListViewState by detailListViewModel.viewState.collectAsState()

    if(detailListViewState.sortBottomSheetState) {
        EveryMealSortCategoryBottomSheetDialog(
            detailListViewState.detailSortCategoryType.title(),
            onClick = {
                detailListViewModel.setEvent(DetailContract.DetailEvent.OnClickDetailListCategoryType(it.DetailSortCategoryType()))
                detailListViewModel.setEvent(DetailContract.DetailEvent.SortBottomSheetStateChange(false))
            },
            onDismiss = {
                detailListViewModel.setEvent(DetailContract.DetailEvent.SortBottomSheetStateChange(false))
            }
        )
    }

    if(detailListViewState.mealRatingBottomSheetState) {
        EveryMealCategoryRatingBottomSheetDialog(
            onClick = {
                detailListViewModel.setEvent(DetailContract.DetailEvent.MealRatingBottomSheetStateChange(false))
            },
            onDismiss = {
                detailListViewModel.setEvent(DetailContract.DetailEvent.MealRatingBottomSheetStateChange(false))
            },
            onCategoryClick = {

            },
            onRatingClick = {

            }
        )
    }

    if(detailListViewState.reportBottomSheetState) {
        EveryMealReportBottomSheetDialog(
            onClick = {
                detailListViewModel.setEvent(DetailContract.DetailEvent.ReportBottomSheetStateChange(false))
                detailListViewModel.setEvent(DetailContract.DetailEvent.DetailReportBottomSheetStateChange(true))
            },
            onDismiss = {
                detailListViewModel.setEvent(DetailContract.DetailEvent.ReportBottomSheetStateChange(false))
            }
        )
    }

    if(detailListViewState.detailReportBottomSheetState) {
        EveryMealDetailReportBottomSheetDialog(
            onClick = {
                detailListViewModel.setEvent(DetailContract.DetailEvent.DetailReportBottomSheetStateChange(false))
            },
            onDismiss = {
                detailListViewModel.setEvent(DetailContract.DetailEvent.DetailReportBottomSheetStateChange(false))
            },
            onReportCategoryClick = {

            }
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White),
    ) {
        Scaffold(
            topBar = {
                SaveTopBar(title = title) {
                    navigateToPreviousScreen()
                }
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(horizontal = 20.dp),
            ) {
                Row {
                    DetailScreenChip(
                        title = detailListViewState.detailSortCategoryType.title(),
                        isCategory = true,
                        onChipClicked = {
                            detailListViewModel.setEvent(DetailContract.DetailEvent.SortBottomSheetStateChange(true))
                        }
                    )
                    Spacer(modifier = Modifier.padding(4.dp))
                    DetailScreenChip(
                        title = "필터",
                        isCategory = true,
                        onChipClicked = {
                            detailListViewModel.setEvent(DetailContract.DetailEvent.MealRatingBottomSheetStateChange(true))
                        }
                    )
                    DetailScreenChip(
                        title = "TEST신고버튼",
                        isCategory = true,
                        onChipClicked = {
                            detailListViewModel.setEvent(DetailContract.DetailEvent.ReportBottomSheetStateChange(true))
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun DetailScreenChip(
    title: String,
    isCategory: Boolean,
    onChipClicked: () -> Unit,
) {
    Surface(
        modifier = Modifier.clickable(
            indication = null,
            interactionSource = remember { MutableInteractionSource() }
        ) { onChipClicked() },
        color = Grey2,
        shape = RoundedCornerShape(100.dp),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = title,
                color = Grey7,
                style = Typography.bodySmall,
                modifier = Modifier.padding(start = 12.dp, end = 4.dp, top = 6.dp, bottom = 6.dp)
            )
            if(isCategory) {
                Image(
                    modifier = Modifier
                        .padding(end = 12.dp)
                        .size(12.dp),
                    imageVector = ImageVector.vectorResource(id = R.drawable.icon_arrow_bottom),
                    contentDescription = "arrow_bottom"
                )
            } else {
                Image(
                    modifier = Modifier
                        .padding(end = 12.dp)
                        .size(12.dp),
                    imageVector = ImageVector.vectorResource(id = R.drawable.icon_x_mono_12),
                    contentDescription = "close"
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewDetailListScreen() {
    DetailListScreen(title = "맛집") {

    }
}

@Preview
@Composable
fun PreviewDetailScreenChip() {
    DetailScreenChip(
        title = "최신순",
        isCategory = true,
        onChipClicked = {

        }
    )
}