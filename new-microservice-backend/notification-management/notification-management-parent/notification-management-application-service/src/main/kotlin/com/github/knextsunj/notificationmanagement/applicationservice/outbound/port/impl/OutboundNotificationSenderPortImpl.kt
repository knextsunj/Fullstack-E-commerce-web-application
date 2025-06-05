package com.github.knextsunj.notificationmanagement.applicationservice.outbound.port.impl

import com.github.knextsunj.notificationmanagement.applicationservice.outbound.event.publisher.OutboundNotificationEventPublisher
import com.github.knextsunj.notificationmanagement.coredomain.outbound.port.OutboundNotificationSenderPort
import org.springframework.beans.factory.annotation.Autowired

open class OutboundNotificationSenderPortImpl:OutboundNotificationSenderPort {

    @Autowired
    lateinit var outboundNotificationEventPublisher: OutboundNotificationEventPublisher

    override fun publishNotification(notification: Any?) {
        outboundNotificationEventPublisher.publishOutboundNotificationEvent(notification)
    }
}