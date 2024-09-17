package io.miragon.zeebe.tm.payment.adapter.`in`.zeebe

import io.camunda.zeebe.spring.client.annotation.JobWorker
import io.camunda.zeebe.spring.client.annotation.Variable
import io.miragon.zeebe.tm.payment.application.port.`in`.SendPaymentReceivedUseCase
import io.miragon.zeebe.tm.payment.application.port.`in`.SendPaymentReceivedUseCase.Command
import org.springframework.stereotype.Component

@Component
class PaymentReceivedWorker(
    private val useCase: SendPaymentReceivedUseCase
)
{
    @JobWorker(type = "payment-received")
    fun paymentReceived(@Variable invoiceId: String)
    {
        val command = Command(invoiceId)
        useCase.send(command)
    }
}
