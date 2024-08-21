package io.miragon.zeebe.tm.order.adapter.`in`.zeebe

import io.camunda.zeebe.spring.client.annotation.JobWorker
import io.camunda.zeebe.spring.client.annotation.Variable
import io.miragon.zeebe.tm.order.application.port.`in`.SendCancellationUseCase
import mu.KotlinLogging
import org.springframework.stereotype.Component

@Component
class SendCancellationWorker(
    private val useCase: SendCancellationUseCase,
)
{
    private val log = KotlinLogging.logger {}

    @JobWorker(type = "sendCancellation")
    fun sendCancellation(@Variable("orderId") orderId: String)
    {
        useCase.decline(SendCancellationUseCase.Command(orderId))
        log.info { "Order $orderId declined!" }
    }
}
