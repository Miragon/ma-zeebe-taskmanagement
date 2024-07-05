package io.miragon.zeebe.tm.order.application.port.`in`

import io.miragon.zeebe.tm.order.domain.Form
import io.miragon.zeebe.tm.order.domain.Item

interface LoadPrepareOrderTaskUseCase
{
    fun load(command: Command): Response

    data class Command(
        val orderId: String,
    )

    data class Response(
        val form: Form.JsonForm,
        val items: List<Item>,
    )
}