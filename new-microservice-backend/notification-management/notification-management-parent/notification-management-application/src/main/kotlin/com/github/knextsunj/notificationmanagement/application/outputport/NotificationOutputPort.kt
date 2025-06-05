package com.github.knextsunj.notificationmanagement.application.outputport

interface NotificationOutputPort {

    fun sendNotification(notification: Any?)
}