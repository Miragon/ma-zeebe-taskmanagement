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
    override fun completeCheckOrderTask(id: Long): Long
    {
        completeTaskPort.completeTask(id)
        val order = orderPersistencePort.findById(id)
        order.state = Order.OrderState.PREPARE
        return orderPersistencePort.save(id, order)
    }

    override fun completePrepareOrderTask(id: Long): Long
    {
        completeTaskPort.completeTask(id)
        val order = orderPersistencePort.findById(id)
        order.state = Order.OrderState.DELIVER
        return orderPersistencePort.save(id, order)
    }
}
