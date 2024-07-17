package io.miragon.zeebe.tm.order.adapter.`in`.rest

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import io.miragon.zeebe.tm.order.adapter.`in`.rest.model.FormDto
import io.miragon.zeebe.tm.order.adapter.`in`.rest.model.MessageDto
import io.miragon.zeebe.tm.order.adapter.`in`.rest.model.schema.StartProcessSchema
import io.miragon.zeebe.tm.order.adapter.`in`.rest.model.schema.toCommand
import io.miragon.zeebe.tm.order.application.port.`in`.LoadProcessStartFormUseCase
import io.miragon.zeebe.tm.order.application.port.`in`.StartProcessUseCase
import io.miragon.zeebe.tm.order.domain.Order
import mu.KotlinLogging
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/rest/process")
class StartProcessController(
    private val loadFormUseCase: LoadProcessStartFormUseCase,
    private val startProcessUseCase: StartProcessUseCase
)
{
    private val mapper = jacksonObjectMapper()

    private val logger = KotlinLogging.logger {}

    private val processStartFormPath = "/forms/StartProcessSchema.form.json"

    @GetMapping("/start/form")
    fun loadForm(): ResponseEntity<FormDto<*>>
    {
        val command = LoadProcessStartFormUseCase.Command(
            filePath = processStartFormPath
        )
        val response = loadFormUseCase.load(command)
        val schema = mapper.writeValueAsString(response.form.schema)
        val uiSchema = mapper.writeValueAsString(response.form.uiSchema)

        return ResponseEntity.ok(
            FormDto.JsonFormDto(
                schema = schema,
                uiSchema = uiSchema,
                formData = null
            )
        )
    }

    @PostMapping("/start")
    fun startProcess(@RequestBody formData: StartProcessSchema): ResponseEntity<MessageDto>
    {
        val command = formData.toCommand(Order.OrderState.CHECK)
        val orderId = startProcessUseCase.startProcess(command)
        val response = MessageDto("Order with id $orderId created!")

        logger.info { "Order with id $orderId created!" }

        return ResponseEntity.ok(response)
    }
}