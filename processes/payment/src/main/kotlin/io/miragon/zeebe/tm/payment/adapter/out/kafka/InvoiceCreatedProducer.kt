package io.miragon.zeebe.tm.payment.adapter.out.kafka

import io.miragon.zeebe.tm.payment.application.port.out.InvoiceCreatedPort
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Component
class InvoiceCreatedProducer(
    private val kafkaTemplate: KafkaTemplate<String, InvoiceIdRequest>,
) : InvoiceCreatedPort
{
    override fun handle(invoiceId: String, orderId: String)
    {
        val request = InvoiceIdRequest(invoiceId, orderId)

        kafkaTemplate
            .send("invoice-created", request)
            .join()
    }
}
