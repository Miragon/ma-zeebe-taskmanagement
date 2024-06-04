package io.miragon.application.service

import io.miragon.application.port.`in`.CompleteUserTaskUseCase
import io.miragon.application.port.out.CompleteUserTaskPort
import io.miragon.application.port.out.PersistUserTaskPort
import io.miragon.domain.TaskState
import io.miragon.domain.UserTask
import org.springframework.stereotype.Service

@Service
class CompleteUserTaskService(
    private val completeUserTaskPort: CompleteUserTaskPort,
    private val persistUserTaskPort: PersistUserTaskPort
) : CompleteUserTaskUseCase
{
    override fun complete(command: CompleteUserTaskUseCase.CompleteUserTaskCommand)
    {
        val task = persistUserTaskPort.findByTaskId(command.key)
            ?: throw IllegalArgumentException("Task with key ${command.key} does not exist")

        val variables = task.variables + command.variables

        completeUserTaskPort.complete(command.key, command.variables)
        persistUserTaskPort.save(
            UserTask(
                key = task.key,
                elementId = task.elementId,
                processInstanceKey = task.processInstanceKey,
                bpmnProcessId = task.bpmnProcessId,
                processDefinitionKey = task.processDefinitionKey,
                expiresAt = task.expiresAt,
                variables = variables,
                taskState = TaskState.COMPLETED,
                assignee = task.assignee,
            )
        )
    }

}
