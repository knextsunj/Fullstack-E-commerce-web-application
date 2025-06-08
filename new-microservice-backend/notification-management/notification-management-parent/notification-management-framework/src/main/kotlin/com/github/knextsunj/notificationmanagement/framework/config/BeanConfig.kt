package com.github.knextsunj.notificationmanagement.framework.config

import com.github.knextsunj.notificationmanagement.application.inputport.NotificationInputPort
import com.github.knextsunj.notificationmanagement.application.inputport.impl.NotificationInputPortImpl
import com.github.knextsunj.notificationmanagement.application.outputport.NotificationOutputPort
import com.github.knextsunj.notificationmanagement.application.service.NotificationApplicationService
import com.github.knextsunj.notificationmanagement.application.service.impl.NotificationApplicationServiceImpl
import com.github.knextsunj.notificationmanagement.framework.input.mapper.SendEmailMapperConstructor
import com.github.knextsunj.notificationmanagement.framework.output.notification.impl.NotificationAdapterServiceImpl
import com.github.knextsunj.notificationmanagementdomain.domainservice.NotificationDomainService
import com.github.knextsunj.notificationmanagementdomain.domainservice.impl.NotificationDomainServiceImpl
import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.DependsOn
import org.springframework.context.annotation.PropertySource
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.JavaMailSenderImpl
import java.util.*

@Configuration
@PropertySource("classpath:application.properties")
open class BeanConfig {

    @Value("\${spring.mail.host}")
    lateinit var mailHost: String

    @Value("\${spring.mail.port}")
    var mailPort: Int = 0

    @Value("\${spring.mail.username}")
    lateinit var mailUsername: String

    @Value("\${spring.mail.password}")
    lateinit var mailPassword: String

    @Value("\${spring.mail.properties.mail.smtp.auth}")
    lateinit var mailSmtpAuth: String

    @Value("\${spring.mail.properties.mail.smtp.starttls.enable}")
    lateinit var mailStartTlsEnable: String

//    @Value("\${spring.mail.smtp.ssl.enable}")
//    lateinit var mailSslEnable: String

    @Bean
    open fun notificationDomainService(): NotificationDomainService {
        val notificationDomainService = NotificationDomainServiceImpl()
        return notificationDomainService
    }

    @Bean
    @DependsOn("notificationDomainService","notificationApplicationService")
    open fun notificationInputPort(): NotificationInputPort {
        val notificationInputPort = NotificationInputPortImpl()
        notificationInputPort.notificationDomainService = notificationDomainService()
        notificationInputPort.notificationApplicationService = notificationApplicationService()
        return notificationInputPort
    }

    @Bean
    open fun javaMailSender(): JavaMailSender {
        val mailSender = JavaMailSenderImpl()
        mailSender.host = this.mailHost
        mailSender.port = this.mailPort
        mailSender.username = this.mailUsername
        mailSender.password = this.mailPassword

        val props = Properties()
        props["mail.smtp.auth"] = this.mailSmtpAuth
        props["mail.smtp.starttls.enable"] = this.mailStartTlsEnable
        props["mail.smtp.auth.mechanisms"] = "LOGIN"
        props["mail.transport.protocol"] = "smtp"
        props.put("mail.smtp.sasl.enable", "true")
        props.put("mail.smtp.auth.mechanisms", "XOAUTH2")

        mailSender.javaMailProperties = props

        return mailSender
    }

    @Bean
    @DependsOn("modelMapper")
    open fun notificationOutputPort(): NotificationOutputPort {
        val notificationOutputPort = NotificationAdapterServiceImpl()
        return notificationOutputPort
    }

    @Bean
    open fun modelMapper(): ModelMapper {
        val modelMapper = ModelMapper()
        modelMapper.configuration.fieldAccessLevel = org.modelmapper.config.Configuration.AccessLevel.PRIVATE
        modelMapper.configuration.isFieldMatchingEnabled = true
        modelMapper.configuration.isSkipNullEnabled = true

        modelMapper.addConverter(SendEmailMapperConstructor().buildEmailConvertor())
        return modelMapper
    }

    @Bean
    @DependsOn("notificationOutputPort")
    open fun notificationApplicationService(): NotificationApplicationService {
        val notificationApplicationService = NotificationApplicationServiceImpl()
        notificationApplicationService.notificationOutputPort = notificationOutputPort()
        return notificationApplicationService
    }
 }