package io.miragon.zeebe.tm.order.application.service

import io.miragon.zeebe.tm.order.application.port.`in`.LoadProcessStartFormUseCase
import io.miragon.zeebe.tm.order.application.port.out.FormPersistencePort
import org.springframework.stereotype.Service

@Service
class LoadProcessStartFormService(
    private val formPersistencePort: FormPersistencePort,
) : LoadProcessStartFormUseCase
{
    override fun load(): LoadProcessStartFormUseCase.Response
    {
        val form = formPersistencePort.readProcessStartForm()

        return LoadProcessStartFormUseCase.Response(
            form = form
        )
    }
}