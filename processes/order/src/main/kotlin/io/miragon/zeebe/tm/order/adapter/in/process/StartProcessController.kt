package io.miragon.zeebe.tm.order.adapter.`in`.process

import io.miragon.zeebe.tm.order.adapter.`in`.form.model.OrderSchema
import io.miragon.zeebe.tm.order.application.port.`in`.StartProcessUseCase
import io.miragon.zeebe.tm.order.domain.Order
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/rest/process")
class StartProcessController(private val useCase: StartProcessUseCase)
{
    /**
     * Start the order process.
     * @param payload
     * @return The id of the created order.
     */
    @PostMapping("/start")
    fun placeOrder(@RequestBody payload: OrderSchema): ResponseEntity<StartProcessResult>
    {
        val o = Order(
            customerName = payload.name,
            deliveryAddress = mapOf(
                "street" to payload.address.street,
                "city" to payload.address.city,
                "zipCode" to payload.address.zipCode,
            ),
            items = payload.items.map {
                mapOf(
                    "id" to it.id.toString(),
                    "quantity" to it.quantity.toString(),
                )
            },
            state = Order.OrderState.CHECK
        )

        val startProcessResult = StartProcessResult("Order ${useCase.startProcess(o)} was created.")

        return ResponseEntity.ok(startProcessResult)
    }

    data class StartProcessResult(
        private val returnMessage: String,
    )
}
