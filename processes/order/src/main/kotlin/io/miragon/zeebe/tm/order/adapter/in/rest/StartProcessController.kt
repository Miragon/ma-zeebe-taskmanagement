package io.miragon.zeebe.tm.order.adapter.`in`.rest

import io.miragon.zeebe.tm.order.adapter.`in`.rest.model.FormDto
import io.miragon.zeebe.tm.order.adapter.`in`.rest.model.MessageDto
import io.miragon.zeebe.tm.order.adapter.`in`.rest.model.schema.StartProcessSchema
import io.miragon.zeebe.tm.order.application.port.`in`.LoadProcessStartFormUseCase
import io.miragon.zeebe.tm.order.application.port.`in`.StartProcessUseCase
import io.miragon.zeebe.tm.order.domain.Order
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/rest/process")
class StartProcessController(
    private val loadFormUseCase: LoadProcessStartFormUseCase,
    private val startProcessUseCase: StartProcessUseCase
)
{
    private val processStartFormPath = "/forms/generated/StartProcessSchema.form.json"

    @GetMapping("/start/form")
    fun loadForm(): ResponseEntity<FormDto<*>>
    {
        val command = LoadProcessStartFormUseCase.Command(
            filePath = processStartFormPath
        )
        val response = loadFormUseCase.load(command)
        val form = response.form

        return ResponseEntity.ok(
            FormDto.JsonFormDto(
                schema = form.schema.toString(),
                uiSchema = form.uiSchema.toString(),
                formData = null
            )
        )
    }

    @PostMapping("/start")
    fun placeOrder(@RequestBody formData: Any): ResponseEntity<MessageDto>
    {
        if (formData !is StartProcessSchema)
        {
            return ResponseEntity.badRequest().build()
        }

        val command = formData.toCommand(Order.OrderState.CHECK)
        val orderId = startProcessUseCase.startProcess(command)
        val response = MessageDto("Order with id $orderId created!")

        return ResponseEntity.ok(response)
    }
}