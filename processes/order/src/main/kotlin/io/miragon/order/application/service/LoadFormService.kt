package io.miragon.order.application.service

import io.miragon.order.application.port.`in`.LoadFormUseCase
import io.miragon.order.application.port.out.FormPersistencePort
import io.miragon.order.application.port.out.OrderPersistencePort
import io.miragon.order.domain.Form

class LoadFormService(
    private val formPersistencePort: FormPersistencePort,
    private val orderPersistencePort: OrderPersistencePort,
) : LoadFormUseCase
{
    override fun loadForm(id: String): Form
    {
        TODO("Not yet implemented")
    }
}