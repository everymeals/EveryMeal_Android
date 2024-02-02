package com.everymeal.data.model.review

import com.everymeal.domain.model.review.UserReview
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StoreReviewRequest(
    @SerialName("idx")
    val idx: Int,
    @SerialName("grade")
    val grade: Int,
    @SerialName("content")
    val content: String,
    @SerialName("imageList")
    val imageList: List<String>,
) {
    fun toUserReview(): UserReview {
        return UserReview(
            idx = idx,
            grade = grade,
            content = content,
            imageList = imageList,
        )
    }
}

fun UserReview.toReviewRequest(): StoreReviewRequest {
    return StoreReviewRequest(
        idx = idx,
        grade = grade,
        content = content,
        imageList = imageList,
    )
}
