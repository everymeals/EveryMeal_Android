package com.everymeal.domain.model.auth


data class UserSignUp(
    val emailAuthToken: String,
    val emailAuthValue: String,
    val nickname: String,
    val profileImgKey: String,
    val universityIdx: Int,
)
