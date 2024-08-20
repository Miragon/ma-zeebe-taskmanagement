package io.miragon.zeebe.tm.payment.adapter.`in`.rest

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import io.miragon.zeebe.tm.payment.adapter.`in`.rest.model.CheckPaymentSchema
import io.miragon.zeebe.tm.payment.adapter.`in`.rest.model.CompleteTaskDto
import io.miragon.zeebe.tm.payment.adapter.`in`.rest.model.FormDataDto
import io.miragon.zeebe.tm.payment.adapter.`in`.rest.model.UserTaskDto
import org.springframework.boot.jackson.JsonComponent

@JsonComponent
class CompleteTaskDeserializer : JsonDeserializer<CompleteTaskDto<FormDataDto>>()
{
    private val mapper = jacksonObjectMapper()

    override fun deserialize(p: JsonParser, ctxt: DeserializationContext?): CompleteTaskDto<FormDataDto>
    {
        val node: JsonNode = p.codec.readTree(p)
        val id = node.get("userTask").get("elementId").asText()
        val dataNode = node.get("formData")

        val data: FormDataDto = when (id)
        {
            UserTaskId.CHECK_PAYMENT.id ->
            {
                mapper.treeToValue(dataNode, CheckPaymentSchema::class.java)
            }

            else -> throw IllegalArgumentException("Unknown user task id: $id")
        }

        return CompleteTaskDto(
            userTask = mapper.treeToValue(node.get("userTask"), UserTaskDto::class.java),
            formData = data
        )
    }
}
