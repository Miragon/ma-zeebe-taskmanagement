package io.miragon.zeebe.tm.order.application.service

import io.miragon.zeebe.tm.order.application.port.`in`.LoadCheckOrderTaskUseCase
import io.miragon.zeebe.tm.order.application.port.out.FormPersistencePort
import io.miragon.zeebe.tm.order.application.port.out.OrderPersistencePort
import org.springframework.stereotype.Service

typealias CheckOrderCommand = LoadCheckOrderTaskUseCase.Command
typealias CheckOrderResponse = LoadCheckOrderTaskUseCase.Response

@Service
class LoadCheckOrderTaskService(
    private val formPersistencePort: FormPersistencePort,
    private val orderPersistencePort: OrderPersistencePort,
) : LoadCheckOrderTaskUseCase
{
    override fun load(command: CheckOrderCommand): CheckOrderResponse
    {
        val orderId = command.orderId

        val form = formPersistencePort.readCheckOrderForm()
        val order = orderPersistencePort.findById(orderId)

        return CheckOrderResponse(
            form = form,
            order = order,
        )
    }
}