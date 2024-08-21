package io.miragon.zeebe.tm.order.adapter.out.kafka

import io.miragon.zeebe.tm.order.application.port.out.SendCancellationPort
import mu.KotlinLogging
import org.springframework.stereotype.Component

@Component
class SendCancellationProducer : SendCancellationPort
{
    private val log = KotlinLogging.logger {}

    override fun publish(orderId: String, email: String)
    {
        // TODO produce message to Kafka for sending an email
        log.info { "Declining order $orderId" }
    }
}