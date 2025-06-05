package com.github.knextsunj.notificationmanagementdomain.domain

import com.github.knextsunj.notificationmanagementdomain.constants.NotificationConstants
import com.github.knextsunj.notificationmanagementdomain.exception.ValidationException

/**
 * Value object class for email address.
 */
data class EmailAddress(val toAddress: String?, val fromAddress: String?) {
    fun validateEmailAddressDetails(): Boolean {

        if (null != toAddress && null != fromAddress) {
            if (toAddress.isBlank() || fromAddress.isBlank()) {
                throw ValidationException("From or To address is blank: $toAddress,$fromAddress")
            } else
                if (!toAddress.contains(NotificationConstants.EMAIL_SEPERATOR) || !fromAddress.contains(
                        NotificationConstants.EMAIL_SEPERATOR
                    )
                ) {
                    throw ValidationException("From or To Address not in proper format: $toAddress,$fromAddress")
                }
        } else {
            throw ValidationException("Subject or Content is null")
        }
        return true;
    }
}
