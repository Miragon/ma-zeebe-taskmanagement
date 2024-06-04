package io.miragon.application.port.`in`

interface CompleteUserTaskUseCase
{
    fun complete(command: CompleteUserTaskCommand)

    data class CompleteUserTaskCommand(
        val key: Long,
        val variables: Map<String, Any>
    )
}