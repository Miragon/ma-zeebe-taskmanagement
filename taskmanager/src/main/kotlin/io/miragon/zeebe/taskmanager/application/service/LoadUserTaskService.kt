package io.miragon.zeebe.taskmanager.application.service

import io.miragon.zeebe.taskmanager.application.port.`in`.LoadUserTaskUseCase
import io.miragon.zeebe.taskmanager.application.port.out.UserTaskPersistencePort
import io.miragon.zeebe.taskmanager.domain.UserTask
import org.springframework.stereotype.Service

@Service
class LoadUserTaskService(
    private val userTaskPersistencePort: UserTaskPersistencePort
) : LoadUserTaskUseCase
{
    override fun load(): List<UserTask>
    {
        return userTaskPersistencePort.findAllActiveTasks()
    }
}