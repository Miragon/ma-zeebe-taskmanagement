package io.miragon.order.adapter.`in`.rest

import io.miragon.order.application.port.`in`.StartProcessUseCase
import io.miragon.order.domain.Order
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
     * @param order The order to start the process for.
     * @return The id of the created order.
     */
    @PostMapping("/placeOrder")
    fun placeOrder(@RequestBody order: OrderDto): ResponseEntity<Long>
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

        return ResponseEntity.ok(useCase.startProcess(o))
    }
}
