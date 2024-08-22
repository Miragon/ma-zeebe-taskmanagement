package io.miragon.zeebe.tm.payment.adapter.`in`.kafka

import io.miragon.zeebe.tm.payment.application.port.`in`.CreateInvoiceUseCase
import io.miragon.zeebe.tm.payment.application.port.`in`.CreateInvoiceUseCase.Command
import io.miragon.zeebe.tm.shared.kafka.PaymentRequest
import mu.KotlinLogging
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class PaymentRequestConsumer(
    private val createInvoiceUseCase: CreateInvoiceUseCase
)
{
    private val log = KotlinLogging.logger {}

    @KafkaListener(topics = ["payment-request"], groupId = "payment-process")
    fun consume(request: PaymentRequest)
    {
        val (orderId, amount) = request
        createInvoiceUseCase.create(Command(orderId, amount))
        log.info { "Payment process started for order $orderId with amount $amount" }
    }
}
