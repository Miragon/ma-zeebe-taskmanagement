package io.miragon.zeebe.tm.order.application.service

import io.miragon.zeebe.tm.order.application.port.`in`.RequestPaymentUseCase
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
    override fun request(command: RequestPaymentUseCase.Command): Boolean
    {
        val (orderId) = command
        val order = orderPersistencePort.findById(orderId)

        val amount = order.items.sumOf { it.price ?: BigDecimal(0) }

        // TODO Using Kafka to publish the payment request
        return requestPaymentPort.publish(orderId, amount)
    }
}