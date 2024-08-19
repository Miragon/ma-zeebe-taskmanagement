package io.miragon.zeebe.tm.order.adapter.out.kafka

import io.miragon.zeebe.tm.order.application.port.out.DeclineOrderPort
import mu.KotlinLogging
import org.springframework.stereotype.Component

@Component
class DeclineOrderAdapter : DeclineOrderPort
{
    private val log = KotlinLogging.logger {}

    override fun publish(orderId: String): Boolean
    {
        // TODO: Implement Kafka
        log.info { "Order declined" }
        // val request = DeclineOrderRequest(orderId)
        // kafkaTemplate.send("decline-order-request", request)
        return true
    }
}