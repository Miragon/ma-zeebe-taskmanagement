package io.miragon.zeebe.tm.order.application.port.`in`

interface CompleteTaskUseCase
{
    /**
     * @return The id of the completed task.
     */
    fun completeCheckOrderTask(taskId: Long, orderId: String, approved: Boolean): Long

    /**
     * @return The id of the completed task.
     */
    fun completePrepareOrderTask(taskId: Long, orderId: String, items: List<Map<String, Any>>): Long
}
