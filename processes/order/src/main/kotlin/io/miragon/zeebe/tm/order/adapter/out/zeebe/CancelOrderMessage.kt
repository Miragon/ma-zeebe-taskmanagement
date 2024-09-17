package io.miragon.zeebe.tm.order.adapter.out.zeebe

import io.camunda.zeebe.client.ZeebeClient
import io.miragon.zeebe.tm.order.application.port.out.CancelOrderPort
import org.springframework.stereotype.Component

@Component
class CancelOrderMessage(
    private val zeebeClient: ZeebeClient
) : CancelOrderPort
{
    override fun correlateMessage(orderId: String)
    {
        zeebeClient.newPublishMessageCommand()
            .messageName("CancelOrderEvent")
            .correlationKey(orderId)
            .send()
            .join()
    }
}
