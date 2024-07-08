package io.miragon.zeebe.tm.order.application.service

import io.miragon.zeebe.tm.order.application.port.`in`.LoadPrepareOrderTaskUseCase
import io.miragon.zeebe.tm.order.application.port.`in`.LoadPrepareOrderTaskUseCase.Command
import io.miragon.zeebe.tm.order.application.port.`in`.LoadPrepareOrderTaskUseCase.Response
import io.miragon.zeebe.tm.order.application.port.out.FormPersistencePort
import io.miragon.zeebe.tm.order.application.port.out.OrderPersistencePort
import org.springframework.stereotype.Service

@Service
class LoadPrepareOrderTaskService(
    private val formPersistencePort: FormPersistencePort,
    private val orderPersistencePort: OrderPersistencePort,
) : LoadPrepareOrderTaskUseCase
{
    override fun load(command: Command): Response
    {
        val orderId = command.orderId
        val form = formPersistencePort.readPrepareOrderForm()
        val order = orderPersistencePort.findById(orderId)

        return Response(
            form = form,
            items = order.items
        )
    }
}
