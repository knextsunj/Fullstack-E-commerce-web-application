package com.github.knextsunj.notificationmanagementdomain.domainservice.impl

import com.github.knextsunj.notificationmanagementdomain.domain.Email
import com.github.knextsunj.notificationmanagementdomain.domain.Sms
import com.github.knextsunj.notificationmanagementdomain.domainservice.NotificationDomainService

class NotificationDomainServiceImpl:NotificationDomainService {

    override fun validateNotification(notification: Any?) {

        when(notification) {
            is Email -> {
                notification.emailAddress.validateEmailAddressDetails()
                notification.validateEmailDetails()
            }

            is Sms -> {
                notification.validateSms()
            }
        }
    }
}