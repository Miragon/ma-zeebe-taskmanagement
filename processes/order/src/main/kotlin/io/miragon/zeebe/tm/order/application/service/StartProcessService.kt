package io.miragon.zeebe.tm.order.application.service

import io.miragon.zeebe.tm.order.application.port.`in`.StartProcessUseCase
import io.miragon.zeebe.tm.order.application.port.out.OrderPersistencePort
import io.miragon.zeebe.tm.order.application.port.out.StartProcessPort
import io.miragon.zeebe.tm.order.domain.Order
import org.springframework.stereotype.Service

@Service
class StartProcessService(
    private val orderPersistencePort: OrderPersistencePort,
    private val startProcessPort: StartProcessPort
) : StartProcessUseCase
{
    /**
     * @return order id
     */
    override fun startProcess(order: Order): String
    {
        val orderId = orderPersistencePort.save(order)
        startProcessPort.startProcess(orderId)
        return orderId
    }
}
