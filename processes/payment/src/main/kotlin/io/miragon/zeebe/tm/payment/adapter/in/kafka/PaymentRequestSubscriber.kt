package io.miragon.zeebe.tm.payment.adapter.`in`.kafka

import io.miragon.zeebe.tm.payment.application.port.`in`.CreateInvoiceUseCase
import io.miragon.zeebe.tm.payment.application.port.`in`.CreateInvoiceUseCase.Command
import mu.KotlinLogging
import org.springframework.stereotype.Component

@Component
class PaymentRequestSubscriber(
    private val createInvoiceUseCase: CreateInvoiceUseCase
)
{
    private val log = KotlinLogging.logger {}

    // @KafkaListener(topics = ["payment-request"], groupId = "order")
    // @SendTo("payment-response")
    fun consume(request: PaymentRequest)
    {
        val (orderId, amount) = request
        val response = createInvoiceUseCase.create(Command(orderId, amount))

        val (invoiceId, processInstanceKey) = response
        log.info { "($processInstanceKey) Payment process started for order $orderId with amount $amount" }

        // val response = PaymentResponse(invoiceId)
        // kafkaTemplate.send("payment-response", response)
    }
}
