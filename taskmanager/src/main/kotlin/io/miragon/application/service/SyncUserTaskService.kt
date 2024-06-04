package io.miragon.application.service

import io.miragon.application.port.`in`.SyncUserTaskUseCase
import io.miragon.application.port.out.PersistUserTaskPort
import io.miragon.domain.TaskState
import io.miragon.domain.UserTask
import org.springframework.stereotype.Service

@Service
class SyncUserTaskService(
    private val persistUserTaskPort: PersistUserTaskPort,
) : SyncUserTaskUseCase
{
    override fun sync(command: SyncUserTaskUseCase.SyncUserTaskCommand)
    {
        val matchingTask = persistUserTaskPort.findByTaskId(command.key)
        if (matchingTask == null)
        {
            persistUserTaskPort.save(
                UserTask(
                    key = command.key,
                    elementId = command.elementId,
                    processInstanceKey = command.processInstanceKey,
                    bpmnProcessId = command.bpmnProcessId,
                    processDefinitionKey = command.processDefinitionKey,
                    expiresAt = command.expiresAt,
                    variables = command.variables,
                    taskState = TaskState.CREATED,
                    assignee = null,
                )
            )
        } else
        {
            matchingTask.extendLock(command.expiresAt)
            persistUserTaskPort.save(matchingTask)
        }
    }
}
