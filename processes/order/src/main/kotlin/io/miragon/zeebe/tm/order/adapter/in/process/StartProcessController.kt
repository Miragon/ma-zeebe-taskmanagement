package io.miragon.zeebe.tm.order.adapter.`in`.process

import io.miragon.zeebe.tm.order.adapter.`in`.OrderDto
import io.miragon.zeebe.tm.order.application.port.`in`.StartProcessUseCase
import io.miragon.zeebe.tm.order.domain.Order
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class StartProcessController(private val useCase: StartProcessUseCase)
{
    /**
     * Start the order process.
     * @param order The order to start the process for.
     * @return The id of the created order.
     */
    @PostMapping("/rest/process/start")
    fun placeOrder(@RequestBody order: OrderDto): ResponseEntity<StartProcessResult>
    {
        val o = Order(
            customerName = order.name,
            deliveryAddress = mapOf(
                "street" to order.address.street,
                "city" to order.address.city,
                "zipCode" to order.address.zipCode,
            ),
            items = order.items.map {
                mapOf(
                    "id" to it.id.toString(),
                    "quantity" to it.quantity.toString(),
                )
            },
            state = Order.OrderState.CHECK
        )

        val startProcessResult = StartProcessResult(useCase.startProcess(o))

        return ResponseEntity.ok(startProcessResult)
    }

    data class StartProcessResult(
        private val orderId: String,
    )
}
