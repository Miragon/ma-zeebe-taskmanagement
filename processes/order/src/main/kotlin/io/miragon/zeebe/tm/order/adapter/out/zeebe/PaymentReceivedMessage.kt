package io.miragon.zeebe.tm.order.adapter.out.zeebe

import io.camunda.zeebe.client.ZeebeClient
import io.miragon.zeebe.tm.order.application.port.out.PaymentReceivedPort
import org.springframework.stereotype.Component

@Component
class PaymentReceivedMessage(
    private val zeebeClient: ZeebeClient
) : PaymentReceivedPort
{
    override fun correlateMessage(orderId: String, invoiceId: String)
    {
        zeebeClient.newPublishMessageCommand()
            .messageName("PaymentReceivedEvent")
            .correlationKey(orderId)
            .variables(mapOf("invoiceId" to invoiceId))
            .send()
            .join()
    }
}
