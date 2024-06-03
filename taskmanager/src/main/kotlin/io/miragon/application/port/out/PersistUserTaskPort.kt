package io.miragon.application.port.out

import io.miragon.domain.UserTask

interface PersistUserTaskPort
{
    fun findAll(): List<UserTask>

    fun findByTaskId(taskId: Long): UserTask?

    fun findByAssignee(assignee: String): List<UserTask>

    fun save(task: UserTask)
}
