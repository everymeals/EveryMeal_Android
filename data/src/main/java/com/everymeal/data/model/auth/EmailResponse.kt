package com.everymeal.data.model.auth

import com.everymeal.domain.model.auth.EmailAuthToken
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EmailResponse(
    @SerialName("emailAuthToken") val emailAuthToken: String,
)

fun EmailResponse.toEmailAuthToken(): EmailAuthToken =
    EmailAuthToken(emailAuthToken = emailAuthToken)

