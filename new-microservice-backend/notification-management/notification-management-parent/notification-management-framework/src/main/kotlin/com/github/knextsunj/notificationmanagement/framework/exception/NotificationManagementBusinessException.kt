package com.github.knextsunj.notificationmanagement.framework.exception

open class NotificationManagementBusinessException: Exception {

    constructor(message:String,throwable: Throwable? = null):super(message,throwable)
}