package com.github.knextsunj.notificationmanagement.framework.config

import com.github.knextsunj.notificationmanagement.framework.input.rest.NotificationController
import jakarta.ws.rs.ApplicationPath
import org.glassfish.jersey.server.ResourceConfig
import org.springframework.stereotype.Component

@Component
@ApplicationPath("/notification-management/")
open class NotificationManagementApplication : ResourceConfig {

    constructor():super() {
        register(NotificationController::class.java)
    }
}