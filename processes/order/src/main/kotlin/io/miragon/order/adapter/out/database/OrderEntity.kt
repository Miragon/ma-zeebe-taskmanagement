package io.miragon.order.adapter.out.database

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import jakarta.persistence.*

@Entity
@Table(name = "order_data")
data class OrderEntity(
    @Id
    val id: Long,

    val customerName: String,

    @Convert(converter = MapConverter::class)
    var deliveryAddress: Map<String, Any> = emptyMap(),

    @Convert(converter = ListMapConverter::class)
    var items: List<Map<String, Any>> = emptyList(),

    val state: String,
)

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

@Converter(autoApply = true)
class ListMapConverter : AttributeConverter<List<Map<String, Any>>, String>
{
    private val objectMapper = jacksonObjectMapper()

    override fun convertToDatabaseColumn(map: List<Map<String, Any>>?): String
    {
        return try
        {
            objectMapper.writeValueAsString(map)
        } catch (e: Exception)
        {
            throw IllegalArgumentException("Could not convert map to JSON", e)
        }
    }

    override fun convertToEntityAttribute(json: String?): List<Map<String, Any>>
    {
        return try
        {
            objectMapper.readValue(json, object : TypeReference<List<Map<String, Any>>>()
            {})
        } catch (e: Exception)
        {
            throw IllegalArgumentException("Could not convert JSON to map", e)
        }
    }
}
