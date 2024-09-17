package io.miragon.zeebe.tm.payment.adapter.out.kafka

import io.miragon.zeebe.tm.libs.shared.kafka.CancelOrderRequest
import io.miragon.zeebe.tm.payment.application.port.out.CancelOrderPort
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Component
class CancelOrderProducer(
    private val kafkaTemplate: KafkaTemplate<String, CancelOrderRequest>
) : CancelOrderPort
{
    override fun publish(orderId: String)
    {
        val request = CancelOrderRequest(orderId)

        kafkaTemplate
            .send("cancel-order", request)
            .join()
    }
}
