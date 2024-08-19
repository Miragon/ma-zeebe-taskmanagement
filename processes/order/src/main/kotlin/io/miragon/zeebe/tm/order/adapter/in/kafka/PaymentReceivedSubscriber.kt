package io.miragon.zeebe.tm.order.adapter.`in`.kafka

import io.miragon.zeebe.tm.order.application.port.`in`.PaymentReceivedUseCase
import io.miragon.zeebe.tm.order.application.port.`in`.PaymentReceivedUseCase.Query
import mu.KotlinLogging
import org.springframework.stereotype.Component

@Component
class PaymentReceivedSubscriber(
    private val useCase: PaymentReceivedUseCase
)
{
    private val log = KotlinLogging.logger {}

    // @KafkaListener(topics = ["payment-received"], groupId = "order-process")
    fun receivePayment(response: PaymentIdResponse)
    {
        val paymentId = response.paymentId
        useCase.handle(Query(paymentId))
        log.info { "Payment received $paymentId" }
    }
}
