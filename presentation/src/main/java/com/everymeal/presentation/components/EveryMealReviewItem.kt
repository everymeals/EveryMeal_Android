package com.everymeal.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.everymeal.domain.model.review.StoreReviewEntity
import com.everymeal.presentation.R
import com.everymeal.presentation.ui.theme.Gray300
import com.everymeal.presentation.ui.theme.Gray600
import com.everymeal.presentation.ui.theme.Gray700
import com.everymeal.presentation.ui.theme.Gray800
import com.everymeal.presentation.util.Utils

@Composable
fun EveryMealReviewItem(
    review: StoreReviewEntity,
    onDetailRestaurantClick: (Int) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp)
            .background(color = Color.White)
    ) {
        ReviewTitle(review)
        Spacer(modifier = Modifier.padding(10.dp))
        ReviewContent(review)
        Spacer(modifier = Modifier.padding(4.dp))
        ReviewGoodCount(review)
        Spacer(modifier = Modifier.padding(6.dp))
        ReviewDetailRestaurant(review, onDetailRestaurantClick)
    }
}

@Composable
fun ReviewTitle(review: StoreReviewEntity) {
    Row(
        modifier = Modifier.fillMaxWidth(),
    ) {
        AsyncImage(
            modifier = Modifier
                .size(40.dp)
                .align(alignment = Alignment.CenterVertically),
            model = review.profileImageUrl,
            contentDescription = stringResource(id = R.string.home_review_profile_image_description)
        )
        Spacer(modifier = Modifier.padding(end = 8.dp))
        Column(
            modifier = Modifier
                .weight(1f)
                .align(alignment = Alignment.CenterVertically)
        ) {
            Text(
                text = review.nickName,
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                color = Gray800,
            )
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                items(review.grade) {
                    Image(
                        modifier = Modifier
                            .size(12.dp)
                            .padding(end = 2.dp),
                        painter = painterResource(id = R.drawable.icon_active_star_mono),
                        contentDescription = stringResource(id = R.string.home_review_profile_review_score_description)
                    )
                }
                item {
                    Spacer(modifier = Modifier.padding(end = 4.dp))
                    Text(
                        text = Utils.getTimeAgo(review.createdAt),
                        fontSize = 12.sp,
                        color = Gray600,
                    )
                }
            }
        }
        Image(
            modifier = Modifier
                .size(20.dp)
                .padding(top = 4.dp),
            imageVector = ImageVector.vectorResource(id = R.drawable.icon_dots_mono),
            contentDescription = stringResource(id = R.string.home_review_profile_more_option_description)
        )
    }
}

@Composable
fun ReviewContent(review: StoreReviewEntity) {
    Row(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Text(
            modifier = Modifier
                .weight(1f),
            text = review.content,
            fontSize = 14.sp,
            color = Gray800,
            maxLines = 3,
            overflow = TextOverflow.Ellipsis
        )
        Spacer(modifier = Modifier.padding(end = 10.dp))
        review.images?.let {
            AsyncImage(
                modifier = Modifier
                    .size(64.dp)
                    .align(alignment = Alignment.CenterVertically),
                model = it[0],
                contentDescription = stringResource(id = R.string.home_review_image)
            )
        }
    }
}

@Composable
fun ReviewGoodCount(review: StoreReviewEntity) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            modifier = Modifier
                .size(16.dp),
            imageVector = ImageVector.vectorResource(id = R.drawable.icon_thumb_up_mono),
            contentDescription = stringResource(id = R.string.home_review_good_count)
        )
        Spacer(modifier = Modifier.padding(end = 2.dp))
        Text(
            text = review.reviewMarksCnt.toString(),
            fontSize = 12.sp,
            color = Gray600,
        )
    }
}

@Composable
fun ReviewDetailRestaurant(
    review: StoreReviewEntity,
    onDetailRestaurantClick: (Int) -> Unit
) {
    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .fillMaxWidth()
            .clickable { onDetailRestaurantClick(review.storeIdx) }
            .background(Gray300),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            modifier = Modifier
                .padding(vertical = 12.dp, horizontal = 10.dp)
                .size(24.dp),
            imageVector = ImageVector.vectorResource(id = R.drawable.icon_pin_location_mono),
            contentDescription = stringResource(id = R.string.home_review_detail_restaurant)
        )
        Text(
            modifier = Modifier
                .weight(1f),
            text = review.storeName,
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            color = Gray700,
        )
        Image(
            modifier = Modifier
                .padding(end = 12.dp)
                .size(16.dp),
            imageVector = ImageVector.vectorResource(id = R.drawable.icon_arrow_right),
            contentDescription = stringResource(id = R.string.home_review_detail_restaurant)
        )
    }
}