package com.everymeal.domain

interface NetworkPreference {
    var accessToken: String
    var refreshToken: String
    var nickname: String
    var universityIdx: Int
    var autoLoginConfigured: Boolean
    var profileImgUrl: String
    fun clear()
}
