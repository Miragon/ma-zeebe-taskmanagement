package io.miragon.zeebe.tm.payment.adapter.out.zeebe

import io.camunda.zeebe.client.ZeebeClient
import io.miragon.zeebe.tm.payment.application.port.out.CompleteTaskPort
import org.springframework.stereotype.Component

@Component
class CompleteTaskAdapter(
    private val zeebeClient: ZeebeClient
) : CompleteTaskPort
{
    override fun completeCheckPaymentTask(taskId: Long, approved: Boolean)
    {
        val variables = mapOf("isAccepted" to approved)
        zeebeClient
            .newCompleteCommand(taskId)
            .variables(variables)
            .send()
            .join()
    }
}