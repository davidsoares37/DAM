package com.example.lifecyclemonitor

import com.example.lifecyclemonitor.model.EntryType
import com.example.lifecyclemonitor.model.LogEntry
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

object LifecycleLogger {
    private val _logs = MutableStateFlow<List<LogEntry>>(emptyList())
    val logs: StateFlow<List<LogEntry>> = _logs.asStateFlow()

    private val _autoScroll = MutableStateFlow(true)
    val autoScroll: StateFlow<Boolean> = _autoScroll.asStateFlow()

    fun logEvent(
        activityName: String,
        eventName: String,
        colorRes: Int,
        iconRes: Int,
        eventColorRes: Int
    ) {
        val entry = LogEntry(
            type = EntryType.LOG,
            activityName = activityName,
            eventName = eventName,
            colorRes = colorRes,
            iconRes = iconRes,
            eventColorRes = eventColorRes
        )
        _logs.update { currentLogs -> currentLogs + entry }
    }

    fun addSeparator() {
        val entry = LogEntry(
            type = EntryType.SEPARATOR
        )
        _logs.update { currentLogs -> currentLogs + entry }
    }

    fun clearLogs() {
        _logs.update { emptyList() }
    }

    fun toggleAutoScroll() {
        _autoScroll.update { !it }
    }
}
