package io.miragon.zeebe.tm.payment.adapter.out.kafka

import io.miragon.zeebe.tm.libs.shared.kafka.PaymentReceivedRequest
import io.miragon.zeebe.tm.payment.application.port.out.PaymentReceivedPort
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Component
class PaymentReceivedProducer(
    private val kafkaTemplate: KafkaTemplate<String, PaymentReceivedRequest>
) : PaymentReceivedPort
{
    override fun publish(invoiceId: String, orderId: String)
    {
        val request = PaymentReceivedRequest(invoiceId, orderId)

        kafkaTemplate
            .send("payment-received", request)
            .join()
    }
}
