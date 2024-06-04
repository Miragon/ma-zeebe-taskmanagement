package io.miragon.application.service

import io.miragon.application.port.`in`.LoadUserTaskUseCase
import io.miragon.application.port.out.UserTaskPersistencePort
import io.miragon.domain.UserTask
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