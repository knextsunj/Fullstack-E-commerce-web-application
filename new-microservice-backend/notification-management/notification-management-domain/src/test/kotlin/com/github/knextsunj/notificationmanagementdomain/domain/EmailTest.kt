package com.github.knextsunj.notificationmanagementdomain.domain

import com.github.knextsunj.notificationmanagementdomain.exception.ValidationException
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.util.*

class EmailTest {

    @Test
    fun testForValidEmailDetails() {
        val emailAddress = EmailAddress("hello@yahoo.com","hello1@yahoo.com")
        val email = Email("senderName",emailAddress,"subject","content")
        Assertions.assertTrue(email.validateEmailDetails())
    }

    @Test
    fun testForNullSubject() {
        val emailAddress = EmailAddress("hello@yahoo.com","hello1@yahoo.com")
        val email = Email("senderName",emailAddress,null,"content")
        Assertions.assertThrows(ValidationException::class.java,{email.validateEmailDetails()})
    }

    @Test
    fun testForNullContent() {
        val emailAddress = EmailAddress("hello@yahoo.com","hello1@yahoo.com")
        val email = Email("senderName",emailAddress,"subject",null)
        Assertions.assertThrows(ValidationException::class.java,{email.validateEmailDetails()})
    }

    @Test
    fun testForBlankContent() {
        val emailAddress = EmailAddress("hello@yahoo.com","hello1@yahoo.com")
        val email = Email("senderName",emailAddress,"subject","")
        Assertions.assertThrows(ValidationException::class.java,{email.validateEmailDetails()})
    }

    @Test
    fun testForBlankSubject() {
        val emailAddress = EmailAddress("hello@yahoo.com","hello1@yahoo.com")
        val email = Email("senderName",emailAddress,"","content")
        Assertions.assertThrows(ValidationException::class.java,{email.validateEmailDetails()})
    }

    @Test
    fun testForEmailUUIDId() {
        val uuid = UUID.randomUUID()
        val emailAddress = EmailAddress("hello@yahoo.com","hello1@yahoo.com")
        val email = Email("senderName",emailAddress,"subject","content",uuid)
        Assertions.assertTrue(email.validateEmailDetails())
        Assertions.assertEquals(uuid,email.id)
    }
}