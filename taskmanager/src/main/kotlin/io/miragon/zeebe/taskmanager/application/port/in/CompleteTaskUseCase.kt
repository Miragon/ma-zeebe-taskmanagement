package io.miragon.zeebe.taskmanager.application.port.`in`

interface CompleteTaskUseCase
{
    fun complete(command: Command)

    data class Command(
        val key: Long,
    )
}