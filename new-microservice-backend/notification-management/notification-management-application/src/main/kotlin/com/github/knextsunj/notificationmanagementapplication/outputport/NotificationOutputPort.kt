package com.github.knextsunj.notificationmanagementapplication.outputport

interface NotificationOutputPort {

    fun sendNotification(notification: Any?)
}