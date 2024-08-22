package io.miragon.zeebe.tm.order.adapter.`in`.kafka

import io.miragon.zeebe.tm.order.application.port.`in`.InvoiceCreatedUseCase
import io.miragon.zeebe.tm.order.application.port.`in`.InvoiceCreatedUseCase.Command
import io.miragon.zeebe.tm.shared.kafka.InvoiceIdRequest
import mu.KotlinLogging
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class InvoiceCreatedConsumer(
    private val useCase: InvoiceCreatedUseCase
)
{
    private val log = KotlinLogging.logger {}

    @KafkaListener(topics = ["invoice-created"], groupId = "order-process")
    fun invoiceCreated(response: InvoiceIdRequest)
    {
        val (invoiceId, orderId) = response
        useCase.handle(Command(invoiceId, orderId))
        log.info { "Invoice created $invoiceId" }
    }
}
