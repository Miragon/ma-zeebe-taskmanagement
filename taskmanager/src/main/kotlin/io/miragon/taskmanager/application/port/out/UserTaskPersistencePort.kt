package io.miragon.taskmanager.application.port.out

import io.miragon.taskmanager.domain.UserTask

interface UserTaskPersistencePort
{
    fun findAllActiveTasks(): List<UserTask>

    fun findByTaskId(taskId: Long): UserTask?

    fun findByAssignee(assignee: String): List<UserTask>

    fun save(task: UserTask)
}
