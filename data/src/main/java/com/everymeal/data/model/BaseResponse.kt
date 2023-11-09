package com.everymeal.data.model

import kotlinx.serialization.Serializable

@Serializable
data class BaseResponse<T>(
    val localDateTime: String,
    val message: String,
    val data: T,
)

fun <T> Result<BaseResponse<T>>.unwrapData(): Result<T> {
    return this.map { it.data }
}