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
    override fun completeCheckOrderTask(id: Long, approved: Boolean): Long
    {
        completeTaskPort.completeCheckOrderTask(id, approved)
        val order = orderPersistencePort.findById(id)
        order.state = Order.OrderState.PREPARE
        orderPersistencePort.save(id, order)
        return id
    }

    override fun completePrepareOrderTask(id: Long, items: List<Map<String, Any>>): Long
    {
        completeTaskPort.completePrepareOrderTask(id)
        val order = orderPersistencePort.findById(id)
        order.state = Order.OrderState.DELIVER
        order.items = items
        orderPersistencePort.save(id, order)
        return id
    }
}
