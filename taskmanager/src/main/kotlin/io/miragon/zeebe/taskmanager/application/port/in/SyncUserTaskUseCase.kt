package io.miragon.zeebe.taskmanager.application.port.`in`

import java.time.Instant

interface SyncUserTaskUseCase
{
    fun sync(command: Command)

    data class Command(
        val key: Long,
        val elementId: String,
        val processInstanceKey: Long,
        val bpmnProcessId: String,
        val processDefinitionKey: Long,
        val variables: Map<String, Any>,
        val expiresAt: Instant,
    )
}
