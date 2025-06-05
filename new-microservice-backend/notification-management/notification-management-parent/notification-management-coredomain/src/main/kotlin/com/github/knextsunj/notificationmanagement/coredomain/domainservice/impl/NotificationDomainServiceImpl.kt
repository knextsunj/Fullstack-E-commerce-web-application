package com.github.knextsunj.notificationmanagementdomain.domainservice.impl

import com.github.knextsunj.notificationmanagement.coredomain.outbound.port.OutboundNotificationSenderPort
import com.github.knextsunj.notificationmanagementdomain.domain.Email
import com.github.knextsunj.notificationmanagementdomain.domain.Sms
import com.github.knextsunj.notificationmanagementdomain.domainservice.NotificationDomainService

class NotificationDomainServiceImpl:NotificationDomainService {

    lateinit var outboundNotificationSenderPort: OutboundNotificationSenderPort

    override fun validateNotification(notification: Any?) {

        /**
         * Below is usage of when expression of Kotlin language. Similar to Java's switch
         */
        when(notification) {
            is Email -> {
                notification.emailAddress.validateEmailAddressDetails()
                notification.validateEmailDetails()
                /**
                 * Invoke via interface the port to push email notification
                 */
                outboundNotificationSenderPort.publishNotification(notification)
            }

            is Sms -> {
                notification.validateSms()
            }
        }
    }
}