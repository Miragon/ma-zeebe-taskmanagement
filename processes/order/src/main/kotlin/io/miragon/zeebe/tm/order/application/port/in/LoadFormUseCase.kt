package io.miragon.zeebe.tm.order.application.port.`in`

import io.miragon.zeebe.tm.order.domain.Form

interface LoadFormUseCase
{
    fun load(orderId: String): Form

    interface LoadCheckOrderFormUseCase : LoadFormUseCase

    interface LoadPrepareOrderFormUseCase : LoadFormUseCase
}
