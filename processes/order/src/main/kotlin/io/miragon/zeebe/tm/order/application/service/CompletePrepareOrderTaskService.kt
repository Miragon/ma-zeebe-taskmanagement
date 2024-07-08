package io.miragon.zeebe.tm.order.application.service

import io.miragon.zeebe.tm.order.application.port.`in`.CompletePrepareOrderTaskUseCase
import io.miragon.zeebe.tm.order.application.port.out.CompleteTaskPort
import io.miragon.zeebe.tm.order.application.port.out.OrderPersistencePort
import io.miragon.zeebe.tm.order.domain.Order
import org.springframework.stereotype.Service

@Service
class CompletePrepareOrderTaskService(
    private val completeTaskPort: CompleteTaskPort,
    private val orderPersistencePort: OrderPersistencePort,
) : CompletePrepareOrderTaskUseCase
{
    override fun complete(command: CompletePrepareOrderTaskUseCase.Command): Long
    {
        val taskId = command.taskId
        val orderId = command.orderId
        val items = command.items

        val order = orderPersistencePort.findById(orderId)
        order.state = Order.OrderState.DELIVER
        order.items = items
        orderPersistencePort.update(orderId, order)
        completeTaskPort.completePrepareOrderTask(taskId)
        return taskId
    }
}
