package com.everymeal.domain.model.review

data class UserReview(
    val mealIdx: Int,
    val grade: Int,
    val content: String,
    val imageList: List<String>
)
