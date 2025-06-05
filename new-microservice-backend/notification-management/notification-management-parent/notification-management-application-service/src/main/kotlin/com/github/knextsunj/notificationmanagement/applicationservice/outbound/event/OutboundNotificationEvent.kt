package com.github.knextsunj.notificationmanagement.applicationservice.outbound.event

import org.springframework.context.ApplicationEvent

open class OutboundNotificationEvent : ApplicationEvent {

    constructor(source: Any?) : super(source)

}
