package com.everymeal.presentation.ui.review.write

import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.everymeal.presentation.R
import com.everymeal.presentation.components.EveryMealTextField
import com.everymeal.presentation.ui.review.RestaurantName
import com.everymeal.presentation.ui.review.RestaurantType
import com.everymeal.presentation.ui.review.ReviewEffect
import com.everymeal.presentation.ui.review.ReviewEvent
import com.everymeal.presentation.ui.review.ReviewState
import com.everymeal.presentation.ui.review.ReviewTopBar
import com.everymeal.presentation.ui.review.ReviewViewModel
import com.everymeal.presentation.ui.review.StarRating
import com.everymeal.presentation.ui.theme.Gray200
import com.everymeal.presentation.ui.theme.Main100

@Composable
fun ReviewWriteScreen(
    viewModel: ReviewViewModel,
) {
    val pickMultipleMedia = rememberLauncherForActivityResult(
        ActivityResultContracts.PickMultipleVisualMedia(10),
    ) {
        viewModel.setEvent(ReviewEvent.OnImageSelected(it))
    }
    val viewState by viewModel.viewState.collectAsState()
    val context = LocalContext.current

    Scaffold(
        topBar = {
            ReviewTopBar(
                title = stringResource(R.string.review_write),
                onBackClicked = {},
            )
        },
        containerColor = Color.White,
    ) { innerPadding ->
        ReviewWriteContract(
            modifier = Modifier.padding(innerPadding),
            viewState = viewState,
            reviewTextChanged = {
                viewModel.setEvent(ReviewEvent.OnReviewTextChanged(it))
            },
            onAddPhotoClicked = {
                pickMultipleMedia.launch(
                    PickVisualMediaRequest(
                        ActivityResultContracts.PickVisualMedia.ImageOnly,
                    ),
                )
            },
            onReviewRegisterClicked = {
                viewModel.setEvent(ReviewEvent.OnReviewSave)
            }
        )
    }
    LaunchedEffect(Unit) {
        viewModel.effect.collect {
            when (it) {
                is ReviewEffect.OnReviewSaveSuccess -> {
                    Toast.makeText(
                        context,
                        context.getString(R.string.register_review_success),
                        Toast.LENGTH_SHORT,
                    ).show()
                }

                is ReviewEffect.OnReviewSaveFail -> {
                    Toast.makeText(
                        context,
                        context.getString(R.string.register_review_fail),
                        Toast.LENGTH_SHORT,
                    ).show()
                }
            }
        }

    }
}

@Composable
private fun ReviewWriteContract(
    modifier: Modifier = Modifier,
    viewState: ReviewState,
    reviewTextChanged: (String) -> Unit = {},
    onAddPhotoClicked: () -> Unit = {},
    onReviewRegisterClicked: () -> Unit = {},
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(color = Color.White)
            .padding(horizontal = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {
        RestaurantType(viewState = viewState)
        RestaurantName(
            modifier = Modifier
                .padding(top = 12.dp),
            viewState = viewState,
        )
        StarRating(
            modifier = modifier.padding(top = 16.dp),
            ratingStateList = viewState.starRatingStateList,
            starSize = 20.dp,
        )
        Spacer(modifier = Modifier.height(60.dp))
        EveryMealTextField(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp),
            value = viewState.reviewValue,
            onValueChange = reviewTextChanged,
            placeholderText = stringResource(id = R.string.review_text_placeholder),
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
        ) {
            AddReviewPhoto(
                viewState = viewState,
                addPhotoClicked = onAddPhotoClicked,
            )
            LazyRow {
                items(viewState.imageUri) {
                    Spacer(modifier = Modifier.width(8.dp))
                    AsyncImage(
                        modifier = Modifier
                            .size(91.dp)
                            .clip(RoundedCornerShape(10.dp)),
                        model = it,
                        contentScale = ContentScale.Crop,
                        contentDescription = null,
                    )
                }
            }
        }
        Spacer(modifier = Modifier.weight(1f))
        ReviewRegisterButton(
            modifier = Modifier
                .fillMaxWidth()
                .height(54.dp),
            onReviewRegisterClicked = onReviewRegisterClicked,
        )
        Spacer(modifier = Modifier.height(20.dp))
    }
}

@Composable
private fun AddReviewPhoto(
    modifier: Modifier = Modifier,
    viewState: ReviewState,
    addPhotoClicked: () -> Unit,
) {
    Surface(
        modifier = modifier
            .size(91.dp)
            .clickable { addPhotoClicked() },
        border = BorderStroke(
            width = 1.dp,
            color = Gray200,
        ),
        shape = RoundedCornerShape(10.dp),
        color = Color.White,
    ) {
        Column(
            modifier = Modifier,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Image(
                modifier = Modifier.size(24.dp),
                painter = painterResource(id = R.drawable.icon_picture_mono),
                contentDescription = null,
            )
            Text(
                modifier = Modifier.padding(top = 2.dp),
                text = stringResource(id = R.string.review_photo_limit, viewState.imageUri.size),
                style = TextStyle(
                    fontSize = 14.sp,
                    lineHeight = 19.6.sp,
                    fontWeight = FontWeight(400),
                    color = Color.Black,
                ),
            )
        }
    }
}

@Composable
private fun ReviewRegisterButton(
    modifier: Modifier = Modifier,
    onReviewRegisterClicked: () -> Unit,
) {
    Button(
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            containerColor = Main100,
            contentColor = Color.White,
            disabledContainerColor = Gray200,
            disabledContentColor = Color.White,
        ),
        onClick = {
            onReviewRegisterClicked()
        },
        shape = RoundedCornerShape(12.dp),
        content = {
            Text(
                text = stringResource(R.string.register),
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight(500),
                    color = Color.White,
                ),
            )
        },
    )
}
