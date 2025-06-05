package com.github.knextsunj.notificationmanagement.framework.input.service

import com.github.knextsunj.notificationmanagement.framework.input.dto.request.EmailDto
import jakarta.ws.rs.core.Response

interface NotificationService {

    fun processSendEmail(emailRequestDto: EmailDto): Response
}