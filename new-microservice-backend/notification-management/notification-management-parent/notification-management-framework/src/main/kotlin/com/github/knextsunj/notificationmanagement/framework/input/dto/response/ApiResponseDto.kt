package com.github.knextsunj.notificationmanagement.framework.input.dto.response

import com.fasterxml.jackson.annotation.JsonProperty

@JvmRecord
data class ApiResponseDto(@JsonProperty("status") val status:Boolean,@JsonProperty("message")  val message:String)
