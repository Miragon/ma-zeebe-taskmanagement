package io.miragon.zeebe.tm.order.application.port.`in`

import io.miragon.zeebe.tm.order.domain.Item

interface CompletePrepareOrderTaskUseCase
{
    /**
     * @return The id of the completed task.
     */
    fun complete(command: Command): Long

    data class Command(
        val taskId: Long,
        val orderId: String,
        val items: List<Item>,
    )
}