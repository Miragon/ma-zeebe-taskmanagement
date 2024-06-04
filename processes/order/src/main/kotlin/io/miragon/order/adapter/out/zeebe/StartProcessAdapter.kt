package io.miragon.order.adapter.out.zeebe

import io.camunda.zeebe.client.ZeebeClient
import io.miragon.order.application.port.out.StartProcessPort
import org.springframework.stereotype.Component

@Component
class StartProcessAdapter(private val zeebeClient: ZeebeClient) : StartProcessPort
{
    override fun startProcess(): String
    {
        val processInstance = zeebeClient
            .newCreateInstanceCommand()
            .bpmnProcessId("OrderProcess")
            .latestVersion()
            .send()
            .join()

        return processInstance.processInstanceKey.toString()
    }
}