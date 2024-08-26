package io.miragon.zeebe.taskmanager.application.port.out

import io.miragon.zeebe.taskmanager.domain.UserTask

interface UserTaskPersistencePort
{
    fun findAllActiveTasks(): List<UserTask>

    fun findByTaskId(taskId: Long): UserTask

    fun update(updatedTask: UserTask): Long

    fun save(task: UserTask): Long
}
