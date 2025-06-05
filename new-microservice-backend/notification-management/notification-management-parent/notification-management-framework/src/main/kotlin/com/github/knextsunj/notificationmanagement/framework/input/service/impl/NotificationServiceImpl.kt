package com.github.knextsunj.notificationmanagement.framework.input.service.impl

import com.github.knextsunj.notificationmanagement.application.inputport.NotificationInputPort
import com.github.knextsunj.notificationmanagement.framework.input.dto.request.EmailDto
import com.github.knextsunj.notificationmanagement.framework.input.dto.response.ApiResponseDto
import com.github.knextsunj.notificationmanagement.framework.input.service.NotificationService
import com.github.knextsunj.notificationmanagementdomain.domain.Email
import jakarta.ws.rs.core.Response
import org.modelmapper.ModelMapper
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
open class NotificationServiceImpl : NotificationService {

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(NotificationServiceImpl::class.java)
    }

    @Autowired
    lateinit var notificationInputPort: NotificationInputPort

    @Autowired
    lateinit var modelMapper: ModelMapper

    override fun processSendEmail(emailRequestDto: EmailDto): Response {
        try {
            val email = modelMapper.map(emailRequestDto, Email::class.java)
            notificationInputPort.validateNotification(email)
            val apiResponseDto = ApiResponseDto(true, "Email submitted for publication")
            return Response.ok(apiResponseDto).build()
        } catch (ex: Exception) {
            logger.error("Exception is thrown during email validation/publication: ", ex)
            val apiResponseDto = ApiResponseDto(false, "Unable to send email")
            return Response.ok(apiResponseDto).build()
        }
    }
}