package io.miragon.zeebe.taskmanager.application.service

import io.miragon.zeebe.taskmanager.application.port.`in`.SyncUserTaskUseCase
import io.miragon.zeebe.taskmanager.application.port.out.UserTaskPersistencePort
import io.miragon.zeebe.taskmanager.domain.UserTask
import io.miragon.zeebe.taskmanager.domain.UserTaskState
import org.springframework.stereotype.Service

@Service
class SyncUserTaskService(
    private val userTaskPersistencePort: UserTaskPersistencePort,
) : SyncUserTaskUseCase
{
    override fun sync(command: SyncUserTaskUseCase.SyncUserTaskCommand)
    {
        val matchingTask = userTaskPersistencePort.findByTaskId(command.key)
        if (matchingTask == null)
        {
            userTaskPersistencePort.save(
                UserTask(
                    key = command.key,
                    elementId = command.elementId,
                    processInstanceKey = command.processInstanceKey,
                    bpmnProcessId = command.bpmnProcessId,
                    processDefinitionKey = command.processDefinitionKey,
                    expiresAt = command.expiresAt,
                    variables = command.variables,
                    taskState = UserTaskState.CREATED,
                    assignee = null,
                )
            )
        } else
        {
            matchingTask.extendLock(command.expiresAt)
            userTaskPersistencePort.save(matchingTask)
        }
    }
}
