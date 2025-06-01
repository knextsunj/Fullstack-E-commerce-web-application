package com.github.knextsunj.notificationmanagementapplication.inputport.impl

import com.github.knextsunj.notificationmanagementapplication.inputport.NotificationInputPort
import com.github.knextsunj.notificationmanagementdomain.domainservice.NotificationDomainService

open class NotificationInputPortImpl:NotificationInputPort {

    private lateinit var notificationDomainService: NotificationDomainService

    override fun validateNotification(notification: Any?) {
        notificationDomainService.validateNotification(notification)
    }
}