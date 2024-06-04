package io.miragon.order.application.port.`in`

interface CompleteTaskUseCase
{
    fun completeTask(id: Long): Boolean
}
