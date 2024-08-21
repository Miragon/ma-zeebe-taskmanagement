package io.miragon.zeebe.tm.order.application.service

import io.miragon.zeebe.tm.order.application.port.`in`.SendCancellationUseCase
import io.miragon.zeebe.tm.order.application.port.`in`.SendCancellationUseCase.Command
import io.miragon.zeebe.tm.order.application.port.out.OrderPersistencePort
import io.miragon.zeebe.tm.order.application.port.out.SendCancellationPort
import org.springframework.stereotype.Service

@Service
class SendCancellationService(
    private val orderPersistencePort: OrderPersistencePort,
    private val sendCancellationPort: SendCancellationPort,
) : SendCancellationUseCase
{
    override fun decline(command: Command)
    {
        val (orderId) = command
        val order = orderPersistencePort.findById(orderId)

        sendCancellationPort.publish(orderId, order.email)
    }
}