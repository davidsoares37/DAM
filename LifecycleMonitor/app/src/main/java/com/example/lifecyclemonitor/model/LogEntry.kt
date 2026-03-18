package com.example.lifecyclemonitor.model

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes

enum class EntryType {
    LOG, SEPARATOR
}

data class LogEntry(
    val id: Long = System.currentTimeMillis(),
    val type: EntryType,
    val activityName: String? = null,
    val eventName: String? = null,
    val timestamp: Long = System.currentTimeMillis(),
    @ColorRes val colorRes: Int? = null,
    @DrawableRes val iconRes: Int? = null,
    @ColorRes val eventColorRes: Int? = null
)
