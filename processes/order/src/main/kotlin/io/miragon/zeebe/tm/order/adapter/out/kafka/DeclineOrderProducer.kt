package io.miragon.zeebe.tm.order.adapter.out.kafka

import io.miragon.zeebe.tm.order.application.port.out.DeclineOrderPort
import mu.KotlinLogging
import org.springframework.stereotype.Component

@Component
class DeclineOrderProducer : DeclineOrderPort
{
    private val log = KotlinLogging.logger {}

    override fun publish(orderId: String, email: String)
    {
        // TODO produce message to Kafka for sending an email
        log.info { "Declining order $orderId" }
    }
}