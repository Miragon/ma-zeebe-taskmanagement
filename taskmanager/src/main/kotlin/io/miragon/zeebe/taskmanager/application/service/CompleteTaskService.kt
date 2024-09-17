package io.miragon.zeebe.taskmanager.application.service

import io.miragon.zeebe.taskmanager.application.port.`in`.CompleteTaskUseCase
import io.miragon.zeebe.taskmanager.application.port.`in`.CompleteTaskUseCase.Command
import io.miragon.zeebe.taskmanager.application.port.out.CompleteTaskPort
import io.miragon.zeebe.taskmanager.application.port.out.UserTaskPersistencePort
import jakarta.persistence.EntityNotFoundException
import mu.KotlinLogging
import org.springframework.stereotype.Service

@Service
class CompleteTaskService(
    private val userTaskPersistencePort: UserTaskPersistencePort,
    private val completeTaskPort: CompleteTaskPort,
) : CompleteTaskUseCase
{
    private val log = KotlinLogging.logger {}

    override fun complete(command: Command): Boolean
    {
        val (key, variables) = command
        try
        {
            val task = userTaskPersistencePort.findByTaskId(key)
            task.complete()
            val taskId = userTaskPersistencePort.update(task)
            val isCompleted = completeTaskPort.complete(taskId, variables)

            return taskId == key && isCompleted
        } catch (e: EntityNotFoundException)
        {
            log.error { "Task with id ${command.key} not found" }
            return false
        }
    }
}