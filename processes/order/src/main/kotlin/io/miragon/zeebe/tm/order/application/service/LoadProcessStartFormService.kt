package io.miragon.zeebe.tm.order.application.service

import io.miragon.zeebe.tm.order.application.port.`in`.LoadProcessStartFormUseCase
import io.miragon.zeebe.tm.order.application.port.`in`.LoadProcessStartFormUseCase.Command
import io.miragon.zeebe.tm.order.application.port.`in`.LoadProcessStartFormUseCase.Response
import io.miragon.zeebe.tm.order.application.port.out.FormPersistencePort
import org.springframework.stereotype.Service

@Service
class LoadProcessStartFormService(
    private val formPersistencePort: FormPersistencePort,
) : LoadProcessStartFormUseCase
{
    override fun load(command: Command): Response
    {
        val form = formPersistencePort.readProcessStartForm(command.filePath)
        return Response(
            form = form
        )
    }
}