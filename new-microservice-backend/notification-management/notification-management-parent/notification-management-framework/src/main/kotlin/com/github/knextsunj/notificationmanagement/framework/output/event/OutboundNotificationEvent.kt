package com.github.knextsunj.notificationmanagement.framework.output.event

import org.springframework.context.ApplicationEvent

open class OutboundNotificationEvent : ApplicationEvent {

    constructor(source: Any?) : super(source)

}
