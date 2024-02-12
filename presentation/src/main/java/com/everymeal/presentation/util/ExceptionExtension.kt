package com.everymeal.presentation.util

import retrofit2.HttpException

fun Throwable.toHttpErrorCode(): Int = if (this is HttpException) {
    this.code()
} else {
    -1
}
