package io.miragon.order.application.service

import io.miragon.order.application.port.`in`.StartProcessUseCase
import io.miragon.order.application.port.out.OrderPersistencePort
import io.miragon.order.application.port.out.StartProcessPort
import io.miragon.order.domain.Order
import org.springframework.stereotype.Service

@Service
class StartProcessService(
    private val orderPersistencePort: OrderPersistencePort,
    private val startProcessPort: StartProcessPort
) : StartProcessUseCase
{
    override fun startProcess(order: Order): Long
    {
        val processInstanceId = startProcessPort.startProcess()
        return orderPersistencePort.save(processInstanceId, order)
    }
}
