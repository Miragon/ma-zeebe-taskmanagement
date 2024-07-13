package io.miragon.zeebe.tm.order.application.service

import io.miragon.zeebe.tm.order.application.port.`in`.LoadCheckOrderTaskUseCase
import io.miragon.zeebe.tm.order.application.port.`in`.LoadCheckOrderTaskUseCase.Command
import io.miragon.zeebe.tm.order.application.port.`in`.LoadCheckOrderTaskUseCase.Response
import io.miragon.zeebe.tm.order.application.port.out.FormPersistencePort
import io.miragon.zeebe.tm.order.application.port.out.OrderPersistencePort
import org.springframework.stereotype.Service

@Service
class LoadCheckOrderTaskService(
    private val formPersistencePort: FormPersistencePort,
    private val orderPersistencePort: OrderPersistencePort,
) : LoadCheckOrderTaskUseCase
{
    override fun load(command: Command): Response
    {
        val orderId = command.orderId
        val filePath = command.filePath

        val form = formPersistencePort.readCheckOrderForm(filePath)
        val order = orderPersistencePort.findById(orderId)

        return Response(
            form = form,
            order = order,
        )
    }
}