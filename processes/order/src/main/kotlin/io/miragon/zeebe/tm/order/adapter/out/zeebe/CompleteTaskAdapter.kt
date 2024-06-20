package io.miragon.zeebe.tm.order.adapter.out.zeebe

import io.camunda.zeebe.client.ZeebeClient
import io.miragon.zeebe.tm.order.application.port.out.CompleteTaskPort
import org.springframework.stereotype.Component

@Component
class CompleteTaskAdapter(private val zeebeClient: ZeebeClient) : CompleteTaskPort
{
    override fun completeCheckOrderTask(id: Long, approved: Boolean): Boolean
    {
        val variables = mapOf("isOrderValid" to approved)
        zeebeClient
            .newCompleteCommand(id)
            .variables(variables)
            .send()
            .join()

        return true
    }

    override fun completePrepareOrderTask(id: Long): Boolean
    {
        zeebeClient
            .newCompleteCommand(id)
            .send()
            .join()

        return true
    }
}
