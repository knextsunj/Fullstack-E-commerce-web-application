package com.github.knextsunj.notificationmanagement.framework.input.dto.request

import com.fasterxml.jackson.annotation.JsonProperty

@JvmRecord
data class EmailDto(@JsonProperty("toAddress") val toAddress:String?,@JsonProperty("fromAddress") val fromAddress:String?,@JsonProperty("subject") val subject:String?,@JsonProperty("content") val content:String?,@JsonProperty("senderName") val senderName:String?)
