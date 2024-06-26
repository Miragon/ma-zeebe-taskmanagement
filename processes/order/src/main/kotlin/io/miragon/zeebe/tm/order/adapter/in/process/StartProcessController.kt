package io.miragon.zeebe.tm.order.adapter.`in`.process

import io.miragon.zeebe.tm.order.adapter.`in`.form.data.OrderSchema
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
     * @param variables
     * @return The id of the created order.
     */
    @PostMapping("/start")
    fun placeOrder(@RequestBody variables: Map<String, OrderSchema>): ResponseEntity<StartProcessResult>
    {
        val order = variables["order"] ?: throw IllegalArgumentException("Order is missing")
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

        val startProcessResult = StartProcessResult("Order ${useCase.startProcess(o)} was created.")

        return ResponseEntity.ok(startProcessResult)
    }

    data class StartProcessResult(
        private val returnMessage: String,
    )
}
