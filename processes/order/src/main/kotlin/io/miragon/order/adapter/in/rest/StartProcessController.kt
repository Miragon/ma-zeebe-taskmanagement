package io.miragon.order.adapter.`in`.rest

import io.miragon.order.application.port.`in`.StartProcessUseCase
import io.miragon.order.domain.Order
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/process")
class StartProcessController(private val useCase: StartProcessUseCase)
{
    /**
     * Start the order process.
     * @param order The order to start the process for.
     * @return The id of the created order.
     */
    @PutMapping("/placeOrder")
    fun placeOrder(@RequestBody order: OrderDto): ResponseEntity<Long>
    {
        val o = Order(
            customerName = order.customerName,
            deliveryAddress = mapOf(
                "street" to order.deliveryAddress.street,
                "city" to order.deliveryAddress.city,
                "zipCode" to order.deliveryAddress.zipCode,
            ),
            items = order.items.associate { it.id.toString() to it.quantity.toString() },
            state = Order.OrderState.CHECK
        )

        return ResponseEntity.ok(useCase.startProcess(o))
    }
}
