package com.github.knextsunj.notificationmanagement.applicationservice.outbound.event.publisher

import com.github.knextsunj.notificationmanagement.applicationservice.outbound.event.OutboundNotificationEvent
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Component

@Component
open class OutboundNotificationEventPublisher {

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(OutboundNotificationEventPublisher::class.java)
    }

    @Autowired
    val applicationEventPublisher: ApplicationEventPublisher? = null

    fun publishOutboundNotificationEvent(notification: Any?): String {
        logger.info("data published for outbound notification event:{}", notification)
        var outboundNotificationEvent = OutboundNotificationEvent(notification)
        applicationEventPublisher?.publishEvent(outboundNotificationEvent)
        return "OUTBOUND_NOTIFICATION_PUBLISHED"
    }

}