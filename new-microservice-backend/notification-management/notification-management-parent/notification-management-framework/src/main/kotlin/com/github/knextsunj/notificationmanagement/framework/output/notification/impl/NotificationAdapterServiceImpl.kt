package com.github.knextsunj.notificationmanagement.framework.output.notification.impl

import com.github.knextsunj.notificationmanagement.application.outputport.NotificationOutputPort
import com.github.knextsunj.notificationmanagement.framework.exception.NotificationManagementBusinessException
import com.github.knextsunj.notificationmanagementdomain.domain.Email
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.mail.MailException
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper


open class NotificationAdapterServiceImpl: NotificationOutputPort {

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(NotificationAdapterServiceImpl::class.java)
    }


    @Autowired
    lateinit var javaMailSender: JavaMailSender

    override fun sendNotification(notification: Any?) {
        logger.info("Received send notification: $notification")
        when (notification) {

            is Email -> {
                try {

                    val message = javaMailSender.createMimeMessage()
                    val helper = MimeMessageHelper(message);
                    helper.setFrom(notification.emailAddress.fromAddress, notification.senderName)
                    helper.setSubject(notification.subject)
                    helper.setText(notification.content)
                    // commented temporarily until mail provider integration is figured out
//                    javaMailSender.send(message)
                    logger.info("Sending email completed")

                } catch (mailException: MailException) {
                    logger.error("Exception when sending mail", mailException)
                    throw NotificationManagementBusinessException(
                        "Unable to send email with details: $notification",
                        mailException
                    )
                }
            }
        }
    }

}