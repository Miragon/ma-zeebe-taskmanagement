package io.miragon.zeebe.tm.order.application.port.`in`

import io.miragon.zeebe.tm.order.domain.Order

interface StartProcessUseCase
{
    /**
     * @return The id of the created order.
     */
    fun startProcess(command: Command): Response

    data class Command(
        val order: Order
    )

    data class Response(
        val orderId: String
    )
}