package com.everymeal.domain.model.auth

data class User(
    val accessToken: String,
    val nickname: String,
    val profileImg: String,
)
