package com.github.knextsunj.notificationmanagement.application.service.impl

import com.github.knextsunj.notificationmanagement.application.outputport.NotificationOutputPort
import com.github.knextsunj.notificationmanagement.application.service.NotificationApplicationService

open class NotificationApplicationServiceImpl:NotificationApplicationService {

    lateinit var notificationOutputPort: NotificationOutputPort

    override fun publishNotification(notification: Any?) {
        notificationOutputPort.sendNotification(notification)
    }


}