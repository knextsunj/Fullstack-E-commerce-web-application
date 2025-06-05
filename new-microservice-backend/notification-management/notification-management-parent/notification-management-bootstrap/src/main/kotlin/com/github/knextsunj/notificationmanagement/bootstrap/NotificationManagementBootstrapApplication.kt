package com.github.knextsunj.notificationmanagement.bootstrap

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(scanBasePackages = ["com.github.knextsunj.notificationmanagement"])
open class NotificationManagementBootstrapApplication {

	companion object {
		@JvmStatic
		fun main(args: Array<String>) {
			runApplication<NotificationManagementBootstrapApplication>(*args)
		}
	}
}
