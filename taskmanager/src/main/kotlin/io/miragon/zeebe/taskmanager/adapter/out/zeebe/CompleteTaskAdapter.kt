package io.miragon.zeebe.taskmanager.adapter.out.zeebe

import io.camunda.zeebe.client.ZeebeClient
import io.miragon.zeebe.taskmanager.application.port.out.CompleteTaskPort
import mu.KotlinLogging
import org.springframework.stereotype.Component

@Component
class CompleteTaskAdapter(private val zeebeClient: ZeebeClient) :
    CompleteTaskPort
{
    private val log = KotlinLogging.logger {}

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
                    .newUserTaskCompleteCommand(taskId) // TODO: Uncomment if using Zeebe Exporter (Zeebe Task API)
                    // .newCompleteCommand(taskId) // JobWorker Version
                    .send()
                    .join()

            } else
            {
                zeebeClient
                    .newUserTaskCompleteCommand(taskId) // TODO: Uncomment if using Zeebe Exporter (Zeebe Task API)
                    // .newCompleteCommand(taskId) // JobWorker Version
                    .variables(variables)
                    .send()
                    .join()
            }

            return true
        } catch (e: Exception)
        {
            log.error { "Failed to complete task with id $taskId: ${e.message}\n${e.stackTrace}" }
            return false
        }
    }
}
