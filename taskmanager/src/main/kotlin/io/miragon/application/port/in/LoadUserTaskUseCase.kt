package io.miragon.application.port.`in`

import io.miragon.domain.UserTask

interface LoadUserTaskUseCase
{
    fun load(): List<UserTask>
}