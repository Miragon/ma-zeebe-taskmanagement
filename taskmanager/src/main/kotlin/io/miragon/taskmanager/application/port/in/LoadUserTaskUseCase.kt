package io.miragon.taskmanager.application.port.`in`

import io.miragon.taskmanager.domain.UserTask

interface LoadUserTaskUseCase
{
    fun load(): List<UserTask>
}