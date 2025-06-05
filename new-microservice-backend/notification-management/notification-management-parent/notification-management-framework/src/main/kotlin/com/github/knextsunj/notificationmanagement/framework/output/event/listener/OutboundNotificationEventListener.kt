package com.github.knextsunj.notificationmanagement.framework.output.event.listener

import com.github.knextsunj.notificationmanagement.application.outputport.NotificationOutputPort
import com.github.knextsunj.notificationmanagement.framework.exception.NotificationManagementBusinessException
import com.github.knextsunj.notificationmanagement.framework.output.event.OutboundNotificationEvent
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.event.EventListener
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component

@Component
open class OutboundNotificationEventListener {

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(OutboundNotificationEventListener::class.java)
    }

    @Autowired
    lateinit var notificationOutputPort: NotificationOutputPort

    @EventListener
    @Async
    fun handleOutboundEvent(notificationEvent: OutboundNotificationEvent) {
        var notification: Any? = null
        try {
            notification = notificationEvent.source as Any?
            notificationOutputPort.sendNotification(notification)
        } catch (notificationManagementBusinessException: NotificationManagementBusinessException) {
            logger.error("Error in sending notification $notification", notificationManagementBusinessException)
        }
    }
}