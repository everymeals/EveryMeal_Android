package com.everymeal.data.model.auth


import com.everymeal.domain.model.auth.User
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
) {
    fun toUser() = User(
        accessToken = accessToken ?: "",
        nickname = nickname ?: "",
        profileImg = profileImg ?: ""
    )
}
