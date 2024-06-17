package io.miragon.order.application.port.`in`

import io.miragon.order.domain.Order

interface StartProcessUseCase
{
    /**
     * @return The id of the created order.
     */
    fun startProcess(order: Order): String
}