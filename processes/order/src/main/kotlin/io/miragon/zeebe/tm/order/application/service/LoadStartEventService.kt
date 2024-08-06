package io.miragon.zeebe.tm.order.application.service

import io.miragon.zeebe.tm.order.application.port.`in`.LoadStartEventUseCase
import io.miragon.zeebe.tm.order.application.port.`in`.LoadStartEventUseCase.Command
import io.miragon.zeebe.tm.order.application.port.`in`.LoadStartEventUseCase.Response
import io.miragon.zeebe.tm.order.application.port.out.FormPersistencePort
import io.miragon.zeebe.tm.order.application.port.out.ItemPersistencePort
import org.springframework.stereotype.Service

@Service
class LoadStartEventService(
    private val itemPersistencePort: ItemPersistencePort,
    private val formPersistencePort: FormPersistencePort,
) : LoadStartEventUseCase
{
    override fun load(command: Command): Response
    {
        val form = formPersistencePort.readProcessStartForm(command.filePath)
        val items = itemPersistencePort.findAll()

        return Response(
            form = form,
            formData = items,
        )
    }
}