package com.everymeal.domain.model.restaurant

data class Restaurant(
    val idx: Int,
    val name: String,
    val address: String,
    val phoneNumber: String,
    val categoryDetail: String,
    val distance: Int,
    val grade: Float,
    val reviewCount: Int,
    val recommendedCount: Int,
    val images: List<String>?,
    val isLiked: Boolean
)

