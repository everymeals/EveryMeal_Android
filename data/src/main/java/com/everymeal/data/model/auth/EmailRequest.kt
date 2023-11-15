package com.everymeal.data.model.auth


import com.everymeal.domain.model.auth.Email
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EmailRequest(
    @SerialName("email")
    val email: String? = null
)

fun EmailRequest.toEmail() = Email(
    email = email ?: ""
)

fun Email.toEmailRequest() = EmailRequest(
    email = email
)
