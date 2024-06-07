package io.miragon.order.application.port.`in`

import io.miragon.order.domain.Order

interface StartProcessUseCase
{
    fun startProcess(order: Order): String
}