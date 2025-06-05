package com.github.knextsunj.notificationmanagement.application.inputport

interface NotificationInputPort {
    fun validateNotification(notification:Any?)
}