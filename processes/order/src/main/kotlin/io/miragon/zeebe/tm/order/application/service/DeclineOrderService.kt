package io.miragon.zeebe.tm.order.application.service

import io.miragon.zeebe.tm.order.application.port.`in`.DeclineOrderUseCase
import io.miragon.zeebe.tm.order.application.port.`in`.DeclineOrderUseCase.Command
import io.miragon.zeebe.tm.order.application.port.out.DeclineOrderPort
import org.springframework.stereotype.Service

@Service
class DeclineOrderService(
    private val declineOrderPort: DeclineOrderPort,
) : DeclineOrderUseCase
{
    override fun decline(command: Command): Boolean
    {
        val (orderId) = command
        return declineOrderPort.publish(orderId)
    }
}