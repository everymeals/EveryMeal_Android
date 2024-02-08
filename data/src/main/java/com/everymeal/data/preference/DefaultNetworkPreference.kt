package com.everymeal.data.preference

import android.content.SharedPreferences
import androidx.core.content.edit
import com.everymeal.domain.NetworkPreference
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefaultNetworkPreference @Inject constructor(
    private val preferences: SharedPreferences,
) : NetworkPreference {
    override var accessToken: String
        get() = preferences.getString("access_token", "").orEmpty()
        set(value) {
            preferences.edit(commit = true) {
                putString("access_token", value)
            }
        }
    override var refreshToken: String
        get() = preferences.getString("refresh_token", "").orEmpty()
        set(value) {
            preferences.edit(commit = true) {
                putString("refresh_token", value)
            }
        }
    override var nickname: String
        get() = preferences.getString("nickname", "").orEmpty()
        set(value) {
            preferences.edit(commit = true) {
                putString("nickname", value)
            }
        }
    override var universityIdx: Int
        get() = preferences.getInt("universityIdx", -1)
        set(value) {
            preferences.edit(commit = true) {
                putInt("universityIdx", value)
            }
        }
    override var autoLoginConfigured: Boolean
        get() = preferences.getBoolean("auto_login", false)
        set(value) {
            preferences.edit(commit = true) {
                putBoolean("auto_login", value)
            }
        }
    override var profileImgKey: String
        get() = preferences.getString("profileImgKey", "").orEmpty()
        set(value) {
            preferences.edit(commit = true) {
                putString("profileImgKey", value)
            }
        }

    override fun clear() {
        preferences.edit(commit = true) {
            clear()
        }
    }
}
