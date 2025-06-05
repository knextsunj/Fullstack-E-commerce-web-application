package com.github.knextsunj.notificationmanagement.application.inputport.impl

import com.github.knextsunj.notificationmanagement.application.inputport.NotificationInputPort
import com.github.knextsunj.notificationmanagementdomain.domainservice.NotificationDomainService

open class NotificationInputPortImpl:NotificationInputPort {

    lateinit var notificationDomainService: NotificationDomainService

    override fun validateNotification(notification: Any?) {
        notificationDomainService.validateNotification(notification)
    }
}