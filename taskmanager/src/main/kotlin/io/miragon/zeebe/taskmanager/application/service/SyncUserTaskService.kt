package io.miragon.zeebe.taskmanager.application.service

import io.miragon.zeebe.taskmanager.application.port.`in`.SyncUserTaskUseCase
import io.miragon.zeebe.taskmanager.application.port.`in`.SyncUserTaskUseCase.Command
import io.miragon.zeebe.taskmanager.application.port.out.UserTaskPersistencePort
import io.miragon.zeebe.taskmanager.domain.UserTask
import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service

@Service
class SyncUserTaskService(
    private val userTaskPersistencePort: UserTaskPersistencePort,
) : SyncUserTaskUseCase
{
    override fun sync(command: Command)
    {
        try
        {
            val matchingTask = userTaskPersistencePort.findByTaskId(command.key)
            matchingTask.extendLock(command.expiresAt)
            userTaskPersistencePort.update(matchingTask)
        } catch (e: EntityNotFoundException)
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
                    assignee = null,
                )
            )
        }
    }
}
