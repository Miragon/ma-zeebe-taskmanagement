package io.miragon.order.adapter.out.zeebe

import io.camunda.zeebe.client.ZeebeClient
import io.miragon.order.application.port.out.CompleteTaskPort
import org.springframework.stereotype.Component

@Component
class CompleteTaskAdapter(private val zeebeClient: ZeebeClient) : CompleteTaskPort
{
    override fun completeTask(id: Long): Boolean
    {
        val res = zeebeClient
            .newCompleteCommand(id)
            .variables(mapOf("isOrderValid" to true))
            .send()
            .join()

        return true
    }
}
