package io.miragon.zeebe.tm.payment.adapter.out.kafka

import io.miragon.zeebe.tm.payment.application.port.out.InvoiceCreatedPort
import io.miragon.zeebe.tm.shared.kafka.InvoiceIdRequest
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Component
class InvoiceCreatedProducer(
    private val kafkaTemplate: KafkaTemplate<String, InvoiceIdRequest>,
) : InvoiceCreatedPort
{
    override fun publish(invoiceId: String, orderId: String)
    {
        val request = InvoiceIdRequest(invoiceId, orderId)

        kafkaTemplate
            .send("invoice-created", request)
            .join()
    }
}
