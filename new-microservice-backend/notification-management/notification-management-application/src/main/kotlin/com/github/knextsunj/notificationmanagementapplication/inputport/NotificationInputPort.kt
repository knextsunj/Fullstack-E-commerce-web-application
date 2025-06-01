package com.github.knextsunj.notificationmanagementapplication.inputport

import javax.management.Notification

interface NotificationInputPort {
    fun validateNotification(notification:Any?)
}