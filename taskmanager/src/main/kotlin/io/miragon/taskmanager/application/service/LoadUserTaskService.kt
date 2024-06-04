package io.miragon.taskmanager.application.service

import io.miragon.taskmanager.application.port.`in`.LoadUserTaskUseCase
import io.miragon.taskmanager.application.port.out.UserTaskPersistencePort
import io.miragon.taskmanager.domain.UserTask
import org.springframework.stereotype.Service

@Service
class LoadUserTaskService(
    private val userTaskPersistencePort: UserTaskPersistencePort
) : LoadUserTaskUseCase
{
    override fun load(): List<UserTask>
    {
        return userTaskPersistencePort.findAll()
    }
}