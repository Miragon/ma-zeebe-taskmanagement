package io.miragon.order.application.port.`in`

interface CompleteTaskUseCase
{
    fun completeCheckOrderTask(taskId: Long, orderId: String, approved: Boolean): Long

    fun completePrepareOrderTask(taskId: Long, orderId: String, items: List<Map<String, Any>>): Long
}
