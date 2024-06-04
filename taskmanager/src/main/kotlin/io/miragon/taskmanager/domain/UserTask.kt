package io.miragon.taskmanager.domain

import java.time.Instant

enum class TaskState
{
    CREATED,
    ASSIGNED,
    COMPLETED
}

data class UserTask(
    val key: Long,
    val elementId: String,
    val processInstanceKey: Long,
    val bpmnProcessId: String,
    val processDefinitionKey: Long,
    var expiresAt: Instant,
    val variables: Map<String, Any> = emptyMap(),
    var taskState: TaskState = TaskState.CREATED,
    var assignee: String?,
)
{
    fun extendLock(until: Instant)
    {
        this.expiresAt = until
    }
}
