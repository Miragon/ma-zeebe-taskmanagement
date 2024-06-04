package io.miragon.adapter.`in`.zeebe

import io.camunda.zeebe.client.api.response.ActivatedJob
import io.camunda.zeebe.spring.client.annotation.JobWorker
import io.miragon.application.port.`in`.SyncUserTaskUseCase
import mu.KotlinLogging
import org.springframework.stereotype.Component
import java.time.Instant

@Component
class UserTaskListener(
    private val useCase: SyncUserTaskUseCase,
)
{
    val log = KotlinLogging.logger { }

    @JobWorker(type = "io.camunda.zeebe:userTask", timeout = 10000, autoComplete = false)
    fun receiveTask(job: ActivatedJob)
    {
        log.info { "Received task: ${job.key}" }
        useCase.sync(
            SyncUserTaskUseCase.SyncUserTaskCommand(
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