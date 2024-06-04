package io.miragon.adapter.out.zeebe

import io.camunda.zeebe.client.ZeebeClient
import io.miragon.application.port.out.CompleteUserTaskPort
import org.springframework.stereotype.Component

@Component
class CompleteUserTaskAdapter(
    private val zeebeClient: ZeebeClient
) : CompleteUserTaskPort
{
    override fun complete(key: Long, variables: Map<String, Any>)
    {
        zeebeClient.newCompleteCommand(key)
            .variables(variables)
            .send()
            .join()
    }
}
