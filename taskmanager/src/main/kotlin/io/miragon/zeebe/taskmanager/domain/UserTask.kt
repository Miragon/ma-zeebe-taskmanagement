package io.miragon.zeebe.taskmanager.domain

import java.time.Instant

data class UserTask(
    val key: Long,
    val elementId: String,
    val processInstanceKey: Long,
    val bpmnProcessId: String,
    val processDefinitionKey: Long,
    var expiresAt: Instant,
    val variables: Map<String, Any> = emptyMap(),
    var taskState: UserTaskState = UserTaskState.CREATED,
    var assignee: String?,
)
{
    fun extendLock(until: Instant)
    {
        this.expiresAt = until
    }
}
