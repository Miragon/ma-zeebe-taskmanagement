package io.miragon.adapter.out.database

import io.miragon.application.port.out.PersistUserTaskPort
import io.miragon.domain.UserTask
import org.springframework.stereotype.Component

@Component
class UserTaskPersistenceAdapter(
    private val userTaskRepository: UserTaskRepository
) : PersistUserTaskPort
{
    override fun findAll(): List<UserTask>
    {
        return userTaskRepository.findAll().map { it.toDomain() }
    }

    override fun findByTaskId(taskId: Long): UserTask?
    {
        return userTaskRepository.findById(taskId).map { it.toDomain() }.orElse(null)
    }

    override fun findByAssignee(assignee: String): List<UserTask>
    {
        return userTaskRepository.findByAssignee(assignee).map { it.toDomain() }
    }

    override fun save(task: UserTask)
    {
        userTaskRepository.save(
            UserTaskEntity(
                id = task.key,
                elementId = task.elementId,
                processInstanceKey = task.processInstanceKey,
                bpmnProcessId = task.bpmnProcessId,
                processDefinitionKey = task.processDefinitionKey,
                variables = task.variables,
                expiresAt = task.expiresAt,
                assignee = task.assignee,
                taskState = task.taskState.toString()
            )
        )
    }
}
