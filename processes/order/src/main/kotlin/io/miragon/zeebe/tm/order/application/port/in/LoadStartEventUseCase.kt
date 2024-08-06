package io.miragon.zeebe.tm.order.application.port.`in`

import io.miragon.zeebe.tm.order.domain.Form
import io.miragon.zeebe.tm.order.domain.Item

interface LoadStartEventUseCase
{
    fun load(command: Command): Response

    data class Command(
        val filePath: String,
    )

    data class Response(
        val form: Form.HtmlForm,
        val formData: List<Item>
    )
}