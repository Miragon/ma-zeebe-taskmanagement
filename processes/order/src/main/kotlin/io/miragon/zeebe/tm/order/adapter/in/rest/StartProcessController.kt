package io.miragon.zeebe.tm.order.adapter.`in`.rest

import io.miragon.zeebe.tm.order.adapter.`in`.rest.model.FormDto
import io.miragon.zeebe.tm.order.adapter.`in`.rest.model.StartProcessSchema
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
    @GetMapping("/form")
    fun loadForm(): ResponseEntity<FormDto<*>>
    {
        val response = loadFormUseCase.load()
        val form = response.form

        return ResponseEntity.ok(
            FormDto.JsonFormDto(
                schema = form.schema,
                uiSchema = form.uiSchema,
                formData = null
            )
        )
    }

    @PostMapping("/start")
    fun placeOrder(@RequestBody formData: StartProcessSchema): ResponseEntity<ResponseDto>
    {
        val command = formData.toCommand(Order.OrderState.CHECK)
        val orderId = startProcessUseCase.startProcess(command)
        val response = ResponseDto("Order with id $orderId created!")

        return ResponseEntity.ok(response)
    }

    data class ResponseDto(
        val message: String
    )
}