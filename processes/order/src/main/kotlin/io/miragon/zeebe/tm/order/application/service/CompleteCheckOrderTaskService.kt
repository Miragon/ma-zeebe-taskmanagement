package io.miragon.zeebe.tm.order.application.service

import io.miragon.zeebe.tm.order.application.port.`in`.CompleteCheckOrderTaskUseCase
import io.miragon.zeebe.tm.order.application.port.`in`.CompleteCheckOrderTaskUseCase.Command
import io.miragon.zeebe.tm.order.application.port.out.CompleteTaskPort
import io.miragon.zeebe.tm.order.application.port.out.OrderPersistencePort
import io.miragon.zeebe.tm.order.domain.Order
import org.springframework.stereotype.Service

@Service
class CompleteCheckOrderTaskService(
    private val completeTaskPort: CompleteTaskPort,
    private val orderPersistencePort: OrderPersistencePort,
) : CompleteCheckOrderTaskUseCase
{
    override fun complete(command: Command): Long
    {
        val taskId = command.taskId
        val orderId = command.orderId
        val isAccepted = command.isAccepted

        val order = orderPersistencePort.findById(orderId)
        order.state = Order.OrderState.PREPARE
        orderPersistencePort.update(orderId, order)
        completeTaskPort.completeCheckOrderTask(taskId, isAccepted)
        return taskId
    }
}