package com.github.knextsunj.notificationmanagementdomain.domain

import com.github.knextsunj.notificationmanagementdomain.exception.ValidationException
import java.util.UUID

/**
 * Main domain entity for email.
 * Unique ID field = id
 */
@JvmRecord
data class Email(
    val senderName: String?,
    val emailAddress: EmailAddress,
    val subject: String?,
    val content: String?,
    val id: UUID = UUID.randomUUID()
) {
    fun validateEmailDetails():Boolean {
     if(null!=subject && null!=content) {
         if (subject.isBlank() || content.isBlank()) {
             throw ValidationException("One of Mandatory fields not present in draft email - Subject,Content: $subject,$content")
         }
     }
        else {
                throw ValidationException("Either From or To Address is null")
            }
        return true
    }
}
