package com.github.knextsunj.notificationmanagementdomain.domain

import com.github.knextsunj.notificationmanagementdomain.exception.ValidationException

data class Sms(val recipientNumber: Long, val message: String) {

    fun validateSms() {
        if (recipientNumber.toString().length != 10) {
            throw ValidationException("Invalid mobile number")
        }
        if (null != message) {
            if (message.isBlank()) {
                throw ValidationException("Empty or invalid message")

            }
        } else {
            throw ValidationException("Null message")
        }
    }
}
