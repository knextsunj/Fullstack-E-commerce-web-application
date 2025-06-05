package com.github.knextsunj.notificationmanagement.framework.input.rest

import com.github.knextsunj.notificationmanagement.framework.input.dto.request.EmailDto
import com.github.knextsunj.notificationmanagement.framework.input.service.NotificationService
import jakarta.ws.rs.Consumes
import jakarta.ws.rs.POST
import jakarta.ws.rs.Path
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller

@Controller
@Path("/notification")
open class NotificationController {

    @Autowired
    lateinit var notificationService:NotificationService

    @POST
    @Path("/sendEmail")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    fun sendEmail(emailRequestDto: EmailDto): Response {
       return notificationService.processSendEmail(emailRequestDto)
    }

}