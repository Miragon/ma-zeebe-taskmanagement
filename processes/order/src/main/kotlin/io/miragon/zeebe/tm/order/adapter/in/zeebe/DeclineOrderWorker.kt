package io.miragon.zeebe.tm.order.adapter.`in`.zeebe

import io.camunda.zeebe.spring.client.annotation.JobWorker
import io.camunda.zeebe.spring.client.annotation.Variable
import io.miragon.zeebe.tm.order.application.port.`in`.DeclineOrderUseCase
import io.miragon.zeebe.tm.order.application.port.`in`.DeclineOrderUseCase.Query
import mu.KotlinLogging
import org.springframework.stereotype.Component

@Component
class DeclineOrderWorker(
    private val useCase: DeclineOrderUseCase,
)
{
    private val log = KotlinLogging.logger {}

    @JobWorker(type = "decline-order")
    fun declineOrder(@Variable orderId: String)
    {
        useCase.decline(Query(orderId))
        log.info { "Order $orderId declined!" }
    }
}
