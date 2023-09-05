package com.everymeal.presentation.util

import java.time.Duration
import java.time.Instant
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

object Utils {
    fun getTimeAgo(isoTime: String): String {
        val modifiedIsoTime = isoTime.substring(0, isoTime.lastIndexOf(".") + 4) + "Z"
        val time = Instant.parse(modifiedIsoTime)
        val now = Instant.now()
        val duration = Duration.between(time, now)

        return when {
            duration.toMinutes() < 1 -> "방금 전"
            duration.toMinutes() < 60 -> "${duration.toMinutes()}분 전"
            duration.toHours() < 24 -> "${duration.toHours()}시간 전"
            duration.toDays() < 2 -> "어제"
            else -> ZonedDateTime.parse(modifiedIsoTime).format(DateTimeFormatter.ofPattern("M월 d일"))
        }
    }
}

