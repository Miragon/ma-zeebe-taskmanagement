package io.miragon.zeebe.tm.order.adapter.`in`.rest.model

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import io.miragon.zeebe.tm.order.adapter.`in`.rest.UserTaskId
import io.miragon.zeebe.tm.order.adapter.`in`.rest.model.schema.CheckOrderSchema
import io.miragon.zeebe.tm.order.adapter.`in`.rest.model.schema.PrepareOrderSchema
import org.springframework.boot.jackson.JsonComponent

@JsonComponent
class CompleteTaskDeserializer : JsonDeserializer<CompleteTaskDto<*>>()
{
    private val mapper = jacksonObjectMapper()

    override fun deserialize(p: JsonParser, ctxt: DeserializationContext?): CompleteTaskDto<*>
    {
        val node: JsonNode = p.codec.readTree(p)
        val id = node.get("userTask").get("elementId").asText()
        val dataNode = node.get("formData")

        val data: Any = when (id)
        {
            UserTaskId.CHECK_ORDER.id ->
            {
                mapper.treeToValue(dataNode, CheckOrderSchema::class.java)
            }

            UserTaskId.PREPARE_ORDER.id ->
            {
                mapper.treeToValue(dataNode, PrepareOrderSchema::class.java)
            }

            else -> throw IllegalArgumentException("Unknown user task id: $id")
        }

        return CompleteTaskDto(
            userTask = mapper.treeToValue(node.get("userTask"), UserTaskDto::class.java),
            formData = data
        )
    }
}
