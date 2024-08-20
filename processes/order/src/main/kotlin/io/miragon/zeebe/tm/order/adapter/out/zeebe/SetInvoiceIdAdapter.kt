package io.miragon.zeebe.tm.order.adapter.out.zeebe

import io.camunda.zeebe.client.ZeebeClient
import io.miragon.zeebe.tm.order.application.port.out.SetInvoiceIdPort
import org.springframework.stereotype.Component

@Component
class SetInvoiceIdAdapter(
    private val zeebeClient: ZeebeClient
) : SetInvoiceIdPort
{
    override fun setInvoiceId(processInstanceKey: Long, invoiceId: String)
    {
        zeebeClient
            .newSetVariablesCommand(processInstanceKey)
            .variables(mapOf("invoiceId" to invoiceId))
            .send()
            .join()
    }
}