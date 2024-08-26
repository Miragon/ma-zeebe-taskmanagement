package io.miragon.zeebe.taskmanager.domain

import java.time.Instant

data class UserTask(
    val key: Long,
    val elementId: String,
    val processInstanceKey: Long,
    val bpmnProcessId: String,
    val processDefinitionKey: Long,
    val variables: Map<String, Any> = emptyMap(),
    var expiresAt: Instant,
    var taskState: UserTaskState = UserTaskState.CREATED,
    var assignee: String?,
)
{
    fun extendLock(until: Instant)
    {
        this.expiresAt = until
    }

    fun complete()
    {
        this.taskState = UserTaskState.COMPLETED
    }

    enum class UserTaskState
    {
        CREATED,
        ASSIGNED,
        COMPLETED
    }
}
