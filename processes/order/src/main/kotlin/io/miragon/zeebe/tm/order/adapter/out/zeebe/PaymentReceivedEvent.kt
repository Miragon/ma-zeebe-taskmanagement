package io.miragon.zeebe.tm.order.adapter.out.zeebe

import io.camunda.zeebe.client.ZeebeClient
import io.miragon.zeebe.tm.order.application.port.out.PaymentReceivedPort
import org.springframework.stereotype.Component

@Component
class PaymentReceivedEvent(
    private val zeebeClient: ZeebeClient
) : PaymentReceivedPort
{
    override fun correlateMessage(invoiceId: String)
    {
        zeebeClient.newPublishMessageCommand()
            .messageName("PaymentReceived")
            .correlationKey(invoiceId)
            .send()
            .join()
    }
}
