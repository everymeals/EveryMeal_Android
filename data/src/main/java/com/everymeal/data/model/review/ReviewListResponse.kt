package com.everymeal.data.model.review


import com.everymeal.domain.model.review.Review
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ReviewListResponse(
    @SerialName("reviewPagingList")
    val reviewPagingList: List<ReviewPaging>? = null,
    @SerialName("reviewTotalCnt")
    val reviewTotalCnt: Int? = null
) {
    @Serializable
    data class ReviewPaging(
        @SerialName("content")
        val content: String? = null,
        @SerialName("grade")
        val grade: Int? = null,
        @SerialName("imageList")
        val imageList: List<String>? = null,
        @SerialName("mealCategory")
        val mealCategory: String? = null,
        @SerialName("mealType")
        val mealType: String? = null,
        @SerialName("restaurantName")
        val restaurantName: String? = null,
        @SerialName("reviewIdx")
        val reviewIdx: Int? = null,
        @SerialName("reviewMarksCnt")
        val reviewMarksCnt: Int? = null
    ) {
        fun toReviewDetail(): Review.ReviewDetail {
            return Review.ReviewDetail(
                content = content ?: "",
                grade = grade ?: 0,
                imageList = imageList ?: listOf(),
                mealCategory = mealCategory ?: "",
                mealType = mealType ?: "",
                restaurantName = restaurantName ?: "",
                reviewIdx = reviewIdx ?: 0,
                reviewMarksCnt = reviewMarksCnt ?: 0
            )
        }
    }

    fun toReview(): Review {
        return Review(
            reviewPagingList = reviewPagingList?.map { it.toReviewDetail() } ?: listOf(),
            reviewTotalCnt = reviewTotalCnt ?: 0
        )
    }
}
