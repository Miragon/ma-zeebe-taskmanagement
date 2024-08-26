package io.miragon.zeebe.taskmanager.adapter.`in`.zeebe

import io.camunda.zeebe.client.api.response.ActivatedJob
import io.camunda.zeebe.spring.client.annotation.JobWorker
import io.miragon.zeebe.taskmanager.application.port.`in`.SyncUserTaskUseCase
import io.miragon.zeebe.taskmanager.application.port.`in`.SyncUserTaskUseCase.Command
import org.springframework.stereotype.Component
import java.time.Instant

@Component
class UserTaskListener(
    private val useCase: SyncUserTaskUseCase,
)
{
    @JobWorker(type = "io.camunda.zeebe:userTask", timeout = 10000, autoComplete = false)
    fun receiveTask(job: ActivatedJob)
    {
        useCase.sync(
            Command(
                key = job.key,
                elementId = job.elementId,
                processInstanceKey = job.processInstanceKey,
                bpmnProcessId = job.bpmnProcessId,
                processDefinitionKey = job.processDefinitionKey,
                expiresAt = Instant.ofEpochMilli(job.deadline),
                variables = job.variablesAsMap,
            )
        )
    }
}