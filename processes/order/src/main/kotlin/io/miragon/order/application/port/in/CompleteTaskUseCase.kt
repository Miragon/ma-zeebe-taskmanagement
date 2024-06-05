package io.miragon.order.application.port.`in`

interface CompleteTaskUseCase
{
    fun completeCheckOrderTask(id: Long): Long

    fun completePrepareOrderTask(id: Long): Long
}
