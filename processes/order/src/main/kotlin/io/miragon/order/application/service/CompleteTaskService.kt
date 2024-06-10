package io.miragon.order.application.service

import io.miragon.order.application.port.`in`.CompleteTaskUseCase
import io.miragon.order.application.port.out.CompleteTaskPort
import io.miragon.order.application.port.out.OrderPersistencePort
import io.miragon.order.domain.Order
import org.springframework.stereotype.Service

@Service
class CompleteTaskService(
    private val completeTaskPort: CompleteTaskPort,
    private val orderPersistencePort: OrderPersistencePort,
) : CompleteTaskUseCase
{
    override fun completeCheckOrderTask(taskId: Long, orderId: String, approved: Boolean): Long
    {
        val order = orderPersistencePort.findById(orderId)
        order.state = Order.OrderState.PREPARE
        orderPersistencePort.update(orderId, order)
        completeTaskPort.completeCheckOrderTask(taskId, approved)
        return taskId
    }

    override fun completePrepareOrderTask(taskId: Long, orderId: String, items: List<Map<String, Any>>): Long
    {
        val order = orderPersistencePort.findById(orderId)
        order.state = Order.OrderState.DELIVER
        order.items = items
        orderPersistencePort.update(orderId, order)
        completeTaskPort.completePrepareOrderTask(taskId)
        return taskId
    }
}
