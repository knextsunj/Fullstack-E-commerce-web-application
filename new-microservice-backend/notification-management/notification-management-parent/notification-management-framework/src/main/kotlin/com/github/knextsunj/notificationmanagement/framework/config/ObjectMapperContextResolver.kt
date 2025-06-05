package com.github.knextsunj.notificationmanagement.framework.config

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule
import jakarta.ws.rs.ext.ContextResolver
import jakarta.ws.rs.ext.Provider
import org.springframework.stereotype.Component
import java.text.SimpleDateFormat

@Component
@Provider
open class ObjectMapperContextResolver : ContextResolver<ObjectMapper> {

    private val mapper: ObjectMapper
        get() {
            return createObjectMapper()
        }

    override fun getContext(type: Class<*>?): ObjectMapper? {
        return mapper
    }

    private fun createObjectMapper(): ObjectMapper {
        val mapper = ObjectMapper()
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
        mapper.enable(SerializationFeature.INDENT_OUTPUT)
        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT)
        mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS)
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)

        mapper.registerModule(Jdk8Module())
        mapper.registerModule(JavaTimeModule())
        mapper.registerModule(ParameterNamesModule())

        mapper.dateFormat = SimpleDateFormat("dd/MM/yyyy")
        return mapper
    }
}