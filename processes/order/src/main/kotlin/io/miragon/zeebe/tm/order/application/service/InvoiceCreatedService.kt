package io.miragon.zeebe.tm.order.application.service

import io.miragon.zeebe.tm.order.application.port.`in`.InvoiceCreatedUseCase
import io.miragon.zeebe.tm.order.application.port.`in`.InvoiceCreatedUseCase.Command
import io.miragon.zeebe.tm.order.application.port.out.OrderPersistencePort
import io.miragon.zeebe.tm.order.application.port.out.SetInvoiceIdPort
import org.springframework.stereotype.Service

@Service
class InvoiceCreatedService(
    private val orderPersistencePort: OrderPersistencePort,
    private val setInvoiceIdPort: SetInvoiceIdPort,
) : InvoiceCreatedUseCase
{
    override fun handle(command: Command)
    {
        val (invoiceId, orderId) = command

        val order = orderPersistencePort.findById(orderId)
        val processInstanceKey =
            order.processInstanceKey ?: throw IllegalStateException("Order $orderId has no process instance key!")

        setInvoiceIdPort.setInvoiceId(processInstanceKey, invoiceId)
    }
}