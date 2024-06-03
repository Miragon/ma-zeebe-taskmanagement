package io.miragon.domain

import java.time.Instant

data class UserTask(
    val key: Long,
    val elementId: String,
    val processInstanceKey: Long,
    val bpmnProcessId: String,
    val processDefinitionKey: Long,
    val variables: Map<String, Any> = emptyMap(),
    var expiresAt: Instant,
    var assignee: String?,
    var taskState: String = "CREATED"
)
{
    fun extendLock(until: Instant)
    {
        this.expiresAt = until
    }
}
