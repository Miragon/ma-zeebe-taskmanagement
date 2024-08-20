package io.miragon.zeebe.tm.order.adapter.out.zeebe

import io.camunda.zeebe.client.ZeebeClient
import io.miragon.zeebe.tm.order.application.port.out.CompleteTaskPort
import org.springframework.stereotype.Component

@Component
class CompleteTaskAdapter(private val zeebeClient: ZeebeClient) : CompleteTaskPort
{
    override fun completeCheckOrderTask(taskId: Long, approved: Boolean): Boolean
    {
        val variables = mapOf("isOrderValid" to approved)
        zeebeClient
            .newCompleteCommand(taskId)
            .variables(variables)
            .send()
            .join()

        return true
    }

    override fun completePrepareDeliveryTask(taskId: Long): Boolean
    {
        zeebeClient
            .newCompleteCommand(taskId)
            .send()
            .join()

        return true
    }
}
