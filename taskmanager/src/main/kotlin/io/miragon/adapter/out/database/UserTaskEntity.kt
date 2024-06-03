package io.miragon.adapter.out.database

import io.miragon.domain.UserTask
import jakarta.persistence.*
import kotlinx.serialization.builtins.MapSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.encodeToJsonElement
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

    @Convert(converter = MapToJsonConverter::class)
    var variables: Map<String, Any> = emptyMap(),

    var expiresAt: Instant,

    var assignee: String?,

    var taskState: String,

    var createdAt: Instant = Instant.now(),
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
            variables = variables,
            expiresAt = expiresAt,
            assignee = assignee,
            taskState = taskState
        )
    }
}

@Converter(autoApply = true)
class MapToJsonConverter : AttributeConverter<Map<String, Any>, String>
{
    override fun convertToDatabaseColumn(attribute: Map<String, Any>?): String
    {
        return attribute?.let {
            val jsonMap = it.mapValues { entry ->
                Json.encodeToJsonElement(entry.value)
            }
            Json.encodeToString(MapSerializer(String.serializer(), JsonElement.serializer()), jsonMap)
        } ?: "{}"
    }

    override fun convertToEntityAttribute(dbData: String): Map<String, Any>
    {
        return Json.decodeFromString(dbData)
    }
}