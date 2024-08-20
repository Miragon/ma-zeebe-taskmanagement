package io.miragon.zeebe.tm.order.application.service

import io.miragon.zeebe.tm.order.application.port.`in`.RequestPaymentUseCase
import io.miragon.zeebe.tm.order.application.port.`in`.RequestPaymentUseCase.Command
import io.miragon.zeebe.tm.order.application.port.out.OrderPersistencePort
import io.miragon.zeebe.tm.order.application.port.out.RequestPaymentPort
import org.springframework.stereotype.Service
import java.math.BigDecimal

@Service
class RequestPaymentService(
    private val orderPersistencePort: OrderPersistencePort,
    private val requestPaymentPort: RequestPaymentPort,
) : RequestPaymentUseCase
{
    override fun request(command: Command)
    {
        val (orderId) = command
        val order = orderPersistencePort.findById(orderId)
        val amount = order.items.sumOf { it.price ?: BigDecimal(0) }

        requestPaymentPort.publish(orderId, amount)
    }
}