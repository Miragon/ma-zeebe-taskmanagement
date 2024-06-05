package io.miragon.order.application.port.`in`

import io.miragon.order.domain.Form

interface LoadFormUseCase
{
    fun loadForm(id: String): Form
}