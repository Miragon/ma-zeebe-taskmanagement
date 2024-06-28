package io.miragon.zeebe.tm.order.application.service

import io.miragon.zeebe.tm.order.application.port.`in`.LoadFormUseCase
import io.miragon.zeebe.tm.order.application.port.out.FormPersistencePort
import org.springframework.stereotype.Service

@Service
class LoadProcessStartFormService(
    private val formPersistencePort: FormPersistencePort,
) : LoadFormUseCase.LoadProcessStartFormUseCase
{
    override fun load(orderId: String) = formPersistencePort.loadProcessStartForm()
}