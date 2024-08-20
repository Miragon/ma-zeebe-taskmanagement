package io.miragon.zeebe.tm.payment.adapter.out.kafka

import io.miragon.zeebe.tm.payment.application.port.out.PaymentReceivedPort
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Component
class PaymentReceivedProducer(
    private val kafkaTemplate: KafkaTemplate<String, PaymentReceivedRequest>
) : PaymentReceivedPort
{
    override fun handle(invoiceId: String)
    {
        val request = PaymentReceivedRequest(invoiceId)

        kafkaTemplate
            .send("payment-received", request)
            .join()
    }
}
