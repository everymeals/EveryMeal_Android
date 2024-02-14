package com.everymeal.data.model.auth


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SignUpResponse(
    @SerialName("accessToken")
    val accessToken: String? = null,
    @SerialName("nickname")
    val nickname: String? = null,
    @SerialName("profileImg")
    val profileImg: String? = null
)
