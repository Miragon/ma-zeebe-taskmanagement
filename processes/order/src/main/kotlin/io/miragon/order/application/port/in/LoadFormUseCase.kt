package io.miragon.order.application.port.`in`

import io.miragon.order.domain.Form

interface LoadFormUseCase
{
    fun load(orderId: Long): Form

    interface LoadCheckOrderFormUseCase : LoadFormUseCase

    interface LoadPrepareOrderFormUseCase : LoadFormUseCase
}
