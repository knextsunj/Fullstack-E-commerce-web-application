package com.github.knextsunj.notificationmanagement.coredomain.outbound.port

interface OutboundNotificationSenderPort {

    fun publishNotification(notification: Any?)
}