package com.github.knextsunj.notificationmanagementdomain.exception

open class ValidationException: Exception {

    constructor(message:String,throwable: Throwable? = null):super(message,throwable)

}