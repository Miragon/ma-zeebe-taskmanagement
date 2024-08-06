package io.miragon.zeebe.tm.order.application.service

import io.miragon.zeebe.tm.order.application.port.`in`.CompletePrepareOrderTaskUseCase
import io.miragon.zeebe.tm.order.application.port.`in`.CompletePrepareOrderTaskUseCase.Command
import io.miragon.zeebe.tm.order.application.port.out.CompleteTaskPort
import io.miragon.zeebe.tm.order.application.port.out.OrderPersistencePort
import io.miragon.zeebe.tm.order.domain.Order
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.format.DateTimeFormatter


@Service
class CompletePrepareOrderTaskService(
    private val completeTaskPort: CompleteTaskPort,
    private val orderPersistencePort: OrderPersistencePort,
) : CompletePrepareOrderTaskUseCase
{
    override fun complete(command: Command): Long
    {
        val taskId = command.taskId
        val orderId = command.orderId
        val deliveryDate = command.deliverDate

        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd").withLocale(java.util.Locale.GERMANY)

        val order = orderPersistencePort.findById(orderId)
        order.deliveryDate = LocalDate.parse(deliveryDate, formatter)
        order.state = Order.OrderState.PREPARED
        orderPersistencePort.update(orderId, order)

        completeTaskPort.completePrepareOrderTask(taskId)

        return taskId
    }

    override fun update(command: Command): Long
    {
        val taskId = command.taskId
        val orderId = command.orderId

        val order = orderPersistencePort.findById(orderId)
        orderPersistencePort.update(orderId, order)
        return taskId
    }
}
