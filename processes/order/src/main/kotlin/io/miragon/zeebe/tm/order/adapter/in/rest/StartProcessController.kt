package io.miragon.zeebe.tm.order.adapter.`in`.rest

import io.miragon.zeebe.tm.order.adapter.`in`.rest.model.FormDto
import io.miragon.zeebe.tm.order.adapter.`in`.rest.model.OrderSchema
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
                data = null
            )
        )
    }

    @PostMapping("/start")
    fun placeOrder(@RequestBody orderSchema: OrderSchema): ResponseEntity<Unit>
    {
        val command = StartProcessUseCase.Command(
            order = orderSchema.toOrder(Order.OrderState.CHECK)
        )
        val response = startProcessUseCase.startProcess(command)
        val message = "Order with id ${response.orderId} created!"

        return ResponseEntity.ok().build()
    }
}