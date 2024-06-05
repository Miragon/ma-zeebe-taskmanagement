package io.miragon.order.adapter.out.database

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import jakarta.persistence.AttributeConverter
import jakarta.persistence.Converter

@Converter(autoApply = true)
class MapConverter : AttributeConverter<Map<String, Any>, String>
{
    private val objectMapper = jacksonObjectMapper()

    override fun convertToDatabaseColumn(map: Map<String, Any>?): String
    {
        return try
        {
            objectMapper.writeValueAsString(map)
        } catch (e: Exception)
        {
            throw IllegalArgumentException("Could not convert map to JSON", e)
        }
    }

    override fun convertToEntityAttribute(json: String?): Map<String, Any>
    {
        return try
        {
            objectMapper.readValue(json, object : TypeReference<Map<String, Any>>()
            {})
        } catch (e: Exception)
        {
            throw IllegalArgumentException("Could not convert JSON to map", e)
        }
    }
}
