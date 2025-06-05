package com.github.knextsunj.notificationmanagement.framework.config

import com.github.knextsunj.notificationmanagement.application.inputport.NotificationInputPort
import com.github.knextsunj.notificationmanagement.application.inputport.impl.NotificationInputPortImpl
import com.github.knextsunj.notificationmanagement.application.outputport.NotificationOutputPort
import com.github.knextsunj.notificationmanagement.applicationservice.outbound.port.impl.OutboundNotificationSenderPortImpl
import com.github.knextsunj.notificationmanagement.coredomain.outbound.port.OutboundNotificationSenderPort
import com.github.knextsunj.notificationmanagement.framework.input.mapper.SendEmailMapperConstructor
import com.github.knextsunj.notificationmanagement.framework.output.notification.impl.NotificationAdapterServiceImpl
import com.github.knextsunj.notificationmanagementdomain.domainservice.NotificationDomainService
import com.github.knextsunj.notificationmanagementdomain.domainservice.impl.NotificationDomainServiceImpl
import org.modelmapper.ModelMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.DependsOn

@Configuration
class BeanConfig {

    @Bean
    fun notificationDomainService():NotificationDomainService {
        val notificationDomainService = NotificationDomainServiceImpl()
        return notificationDomainService
    }

    @Bean
    @DependsOn("notificationDomainService")
    fun notificationInputPort():NotificationInputPort {
        val notificationInputPort = NotificationInputPortImpl()
        notificationInputPort.notificationDomainService = notificationDomainService()
        return notificationInputPort
    }

    @Bean
    fun notificationOutputPort():NotificationOutputPort {
        val notificationOutputPort = NotificationAdapterServiceImpl()
        return notificationOutputPort
    }

    @Bean
    fun modelMapper():ModelMapper {
        val modelMapper = ModelMapper()
        modelMapper.configuration.fieldAccessLevel = org.modelmapper.config.Configuration.AccessLevel.PRIVATE
        modelMapper.configuration.isFieldMatchingEnabled = true
        modelMapper.configuration.isSkipNullEnabled = true

        modelMapper.addConverter(SendEmailMapperConstructor().buildEmailConvertor())
        return modelMapper
    }

    @Bean
    fun outboundNotificationSenderPort():OutboundNotificationSenderPort {
        val outboundNotificationSenderPort = OutboundNotificationSenderPortImpl()
        return outboundNotificationSenderPort
    }


}