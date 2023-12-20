package com.everymeal.domain.model.review

data class Review(
    val reviewPagingList: List<ReviewDetail>,
    val reviewTotalCnt: Int
) {
    data class ReviewDetail(
        val content: String,
        val grade: Int,
        val imageList: List<String>,
        val mealCategory: String,
        val mealType: String,
        val restaurantName: String,
        val reviewIdx: Int,
        val reviewMarksCnt: Int
    )
}

