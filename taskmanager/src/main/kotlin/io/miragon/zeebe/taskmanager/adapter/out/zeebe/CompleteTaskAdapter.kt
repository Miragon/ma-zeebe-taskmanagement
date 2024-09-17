package io.miragon.zeebe.taskmanager.adapter.out.zeebe

import io.camunda.zeebe.client.ZeebeClient
import io.miragon.zeebe.taskmanager.application.port.out.CompleteTaskPort
import org.springframework.stereotype.Component

@Component
class CompleteTaskAdapter(private val zeebeClient: ZeebeClient) :
    CompleteTaskPort
{
    override fun complete(
        taskId: Long,
        variables: Map<String, Any>?
    ): Boolean
    {
        try
        {
            if (variables == null)
            {
                zeebeClient
                    .newCompleteCommand(taskId)
                    .send()
                    .join()

            } else
            {
                zeebeClient
                    .newCompleteCommand(taskId)
                    .variables(variables)
                    .send()
                    .join()
            }

            return true
        } catch (e: Exception)
        {
            return false
        }
    }
}
