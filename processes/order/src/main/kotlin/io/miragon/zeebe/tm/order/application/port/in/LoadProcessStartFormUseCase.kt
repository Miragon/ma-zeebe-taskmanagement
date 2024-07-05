package io.miragon.zeebe.tm.order.application.port.`in`

import io.miragon.zeebe.tm.order.domain.Form

interface LoadProcessStartFormUseCase
{
    fun load(): Response

    data class Response(
        val form: Form.JsonForm
    )
}