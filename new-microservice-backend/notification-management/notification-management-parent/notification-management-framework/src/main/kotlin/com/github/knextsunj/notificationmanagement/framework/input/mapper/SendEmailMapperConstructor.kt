package com.github.knextsunj.notificationmanagement.framework.input.mapper

import com.github.knextsunj.notificationmanagement.framework.input.dto.request.EmailDto
import com.github.knextsunj.notificationmanagementdomain.domain.Email
import com.github.knextsunj.notificationmanagementdomain.domain.EmailAddress
import org.modelmapper.AbstractConverter

open class SendEmailMapperConstructor {

    fun buildEmailConvertor(): AbstractConverter<EmailDto, Email> {

        /**
         * object is a keyword to create anonymous class in Kotlin
         */
        val converter = object : AbstractConverter<EmailDto, Email>() {
            override fun convert(emailDto: EmailDto): Email {
                val emailAddress = EmailAddress(emailDto.toAddress, emailDto.fromAddress)
                val email = Email(emailDto.senderName, emailAddress, emailDto.subject, emailDto.content)
                return email
            }
        }
        return converter
    }
}