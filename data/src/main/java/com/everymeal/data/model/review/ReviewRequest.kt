package com.everymeal.data.model.review

import com.everymeal.domain.model.review.UserReview
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ReviewRequest(
    @SerialName("mealIdx")
    val mealIdx: Int,
    @SerialName("grade")
    val grade: Int,
    @SerialName("content")
    val content: String,
    @SerialName("imageList")
    val imageList: List<String>
) {
    fun toUserReview(): UserReview {
        return UserReview(
            mealIdx = mealIdx,
            grade = grade,
            content = content,
            imageList = imageList
        )
    }
}

fun UserReview.toReviewRequest(): ReviewRequest {
    return ReviewRequest(
        mealIdx = mealIdx,
        grade = grade,
        content = content,
        imageList = imageList
    )
}
