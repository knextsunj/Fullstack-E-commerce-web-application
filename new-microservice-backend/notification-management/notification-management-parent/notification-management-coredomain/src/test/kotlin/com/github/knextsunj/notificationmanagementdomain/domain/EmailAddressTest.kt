package com.github.knextsunj.notificationmanagementdomain.domain

import com.github.knextsunj.notificationmanagementdomain.exception.ValidationException
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class EmailAddressTest {

    @Test
    fun testForValidEmailAddress() {
        val emailAddress = EmailAddress("hello@yahoo.com", "hello1@yahoo.com")
        Assertions.assertTrue(
            emailAddress.validateEmailAddressDetails(
            )
        )
    }

    @Test
    fun testForInvalidToEmailAddress() {
        val emailAddress = EmailAddress("helloyahoo.com", "hello1@yahoo.com")
        Assertions.assertThrows(
            ValidationException::class.java,
            { emailAddress.validateEmailAddressDetails() })
    }

    @Test
    fun testForInvalidFromEmailAddress() {
        val emailAddress = EmailAddress("hello@yahoo.com", "hello1yahoo.com")
        Assertions.assertThrows(
            ValidationException::class.java,
            { emailAddress.validateEmailAddressDetails() })
    }

    @Test
    fun testForBlankToEmailAddress() {
        val emailAddress = EmailAddress("", "hello@yahoo.com")
        Assertions.assertThrows(
            ValidationException::class.java,
            { emailAddress.validateEmailAddressDetails() })
    }

    @Test
    fun testForBlankFromEmailAddress() {
        val emailAddress = EmailAddress("hello@yahoo.com", "")
        Assertions.assertThrows(
            ValidationException::class.java,
            { emailAddress.validateEmailAddressDetails() })
    }

    @Test
    fun testForNullToEmailAddress() {
        val emailAddress = EmailAddress(null, "hello@yahoo.com")
        Assertions.assertThrows(
            ValidationException::class.java,
            { emailAddress.validateEmailAddressDetails() })
    }

    @Test
    fun testForNullFromEmailAddress() {
        val emailAddress = EmailAddress("hello@yahoo.com", null)
        Assertions.assertThrows(
            ValidationException::class.java,
            { emailAddress.validateEmailAddressDetails() })
    }

}