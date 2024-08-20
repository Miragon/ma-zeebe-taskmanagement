package io.miragon.zeebe.tm.order.application.service

import io.miragon.zeebe.tm.order.application.port.`in`.DeclineOrderUseCase
import io.miragon.zeebe.tm.order.application.port.`in`.DeclineOrderUseCase.Command
import io.miragon.zeebe.tm.order.application.port.out.DeclineOrderPort
import io.miragon.zeebe.tm.order.application.port.out.OrderPersistencePort
import org.springframework.stereotype.Service

@Service
class DeclineOrderService(
    private val orderPersistencePort: OrderPersistencePort,
    private val declineOrderPort: DeclineOrderPort,
) : DeclineOrderUseCase
{
    override fun decline(command: Command)
    {
        val (orderId) = command
        val order = orderPersistencePort.findById(orderId)

        declineOrderPort.publish(orderId, order.email)
    }
}