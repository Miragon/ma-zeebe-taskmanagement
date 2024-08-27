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
        log.info { "To: $email\nSubject: #$orderId declined" }
    }
}