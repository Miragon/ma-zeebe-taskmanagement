package io.miragon.zeebe.taskmanager.adapter.out.database

import io.miragon.zeebe.taskmanager.application.port.out.UserTaskPersistencePort
import io.miragon.zeebe.taskmanager.domain.UserTask
import org.springframework.stereotype.Component
import java.time.Instant

@Component
class UserTaskPersistenceAdapter(
    private val userTaskRepository: UserTaskRepository
) : UserTaskPersistencePort
{
    override fun findAllActiveTasks(): List<UserTask>
    {
        return userTaskRepository.findByExpiresAtAfter(Instant.now()).map { it.toDomain() }
    }

    override fun findByTaskId(taskId: Long): UserTask?
    {
        return userTaskRepository.findById(taskId).map { it.toDomain() }.orElse(null)
    }

    override fun findByAssignee(assignee: String): List<UserTask>
    {
        return userTaskRepository.findByAssignee(assignee).map { it.toDomain() }
    }

    override fun update(task: UserTask)
    {
        this.save(task)
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
