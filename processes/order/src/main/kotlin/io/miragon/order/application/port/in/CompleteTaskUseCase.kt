package io.miragon.order.application.port.`in`

interface CompleteTaskUseCase
{
    fun completeTask(id: String): Boolean
}
