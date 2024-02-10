package com.everymeal.data.model.auth


import com.everymeal.domain.model.auth.UserSignUp
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SignUpRequest(
    @SerialName("emailAuthToken")
    val emailAuthToken: String? = null,
    @SerialName("emailAuthValue")
    val emailAuthValue: String? = null,
    @SerialName("nickname")
    val nickname: String? = null,
    @SerialName("profileImgKey")
    val profileImgKey: String? = null,
    @SerialName("universityIdx")
    val universityIdx: Int? = null
) {
    fun toUserSignUp() = UserSignUp(
        emailAuthToken = emailAuthToken ?: "",
        emailAuthValue = emailAuthValue ?: "",
        nickname = nickname ?: "",
        profileImgKey = profileImgKey ?: "",
        universityIdx = universityIdx ?: 0
    )
}

fun UserSignUp.toSignUpRequest() = SignUpRequest(
    emailAuthToken = emailAuthToken,
    emailAuthValue = emailAuthValue,
    nickname = nickname,
    profileImgKey = profileImgKey,
    universityIdx = universityIdx
)
