package io.miragon.application.port.`in`

import java.time.Instant

interface SyncUserTaskUseCase
{
    fun sync(command: SyncUserTaskCommand)

    data class SyncUserTaskCommand(
        val key: Long,
        val elementId: String,
        val processInstanceKey: Long,
        val bpmnProcessId: String,
        val processDefinitionKey: Long,
        val variables: Map<String, Any>,
        val expiresAt: Instant,
    )
}
