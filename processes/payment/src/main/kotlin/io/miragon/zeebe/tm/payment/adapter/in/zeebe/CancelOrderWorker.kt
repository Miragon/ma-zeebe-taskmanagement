package io.miragon.zeebe.tm.payment.adapter.`in`.zeebe

import io.camunda.zeebe.spring.client.annotation.JobWorker
import io.camunda.zeebe.spring.client.annotation.Variable
import io.miragon.zeebe.tm.payment.application.port.`in`.SendCancellationUseCase
import io.miragon.zeebe.tm.payment.application.port.`in`.SendCancellationUseCase.Command
import org.springframework.stereotype.Component

@Component
class CancelOrderWorker(
    private val useCase: SendCancellationUseCase
)
{
    @JobWorker(type = "cancelOrder")
    fun cancelOrder(@Variable invoiceId: String)
    {
        useCase.send(Command(invoiceId))
    }
}
