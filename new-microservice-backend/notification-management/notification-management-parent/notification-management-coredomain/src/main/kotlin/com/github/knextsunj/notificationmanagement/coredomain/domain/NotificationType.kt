package com.github.knextsunj.notificationmanagementdomain.domain

enum class NotificationType(private val type:String) {

    SMS("Short Message Service"),
    EMAIL("Electronic Mail"),
    UNKNOWN("unknown");

    fun getFormattedName(): String {
        return "Notification Type: $type"
    }
}