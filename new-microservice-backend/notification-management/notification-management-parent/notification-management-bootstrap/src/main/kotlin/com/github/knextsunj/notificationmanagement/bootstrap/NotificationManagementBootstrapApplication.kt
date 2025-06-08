package com.github.knextsunj.notificationmanagement.bootstrap

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.AnnotationConfigApplicationContext

@SpringBootApplication(scanBasePackages = ["com.github.knextsunj.notificationmanagement"])
open class NotificationManagementBootstrapApplication {

	companion object {
		@JvmStatic
		fun main(args: Array<String>) {
			runApplication<NotificationManagementBootstrapApplication>(*args)
			val context: ApplicationContext = AnnotationConfigApplicationContext(NotificationManagementBootstrapApplication::class.java)
			val beanNames = context.beanDefinitionNames

			beanNames.forEach { println(it) }
		}
	}
}
