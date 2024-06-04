package io.miragon.adapter.out.database

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import io.miragon.domain.TaskState
import io.miragon.domain.UserTask
import jakarta.persistence.*
import java.time.Instant

@Entity
@Table(name = "user_task")
class UserTaskEntity(
    @Id
    val id: Long,

    val elementId: String,

    val processInstanceKey: Long,

    val bpmnProcessId: String,

    val processDefinitionKey: Long,

    var expiresAt: Instant,

    var taskState: String,

    @Convert(converter = MapConverter::class)
    var variables: Map<String, Any> = emptyMap(),

    var createdAt: Instant = Instant.now(),

    var assignee: String?,

    )
{
    fun toDomain(): UserTask
    {
        return UserTask(
            key = id,
            elementId = elementId,
            processInstanceKey = processInstanceKey,
            bpmnProcessId = bpmnProcessId,
            processDefinitionKey = processDefinitionKey,
            expiresAt = expiresAt,
            variables = variables,
            taskState = TaskState.valueOf(taskState),
            assignee = assignee,
        )
    }
}

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
