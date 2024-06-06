package io.miragon.order.application.port.`in`

interface CompleteTaskUseCase
{
    fun completeCheckOrderTask(id: Long, approved: Boolean): Long

    fun completePrepareOrderTask(id: Long, items: List<Map<String, Any>>): Long
}
