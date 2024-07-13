package io.miragon.zeebe.tm.order.adapter.`in`.rest.model.schema

import io.miragon.zeebe.tm.order.application.port.`in`.CompletePrepareOrderTaskUseCase
import io.miragon.zeebe.tm.order.domain.Item

data class PrepareOrderSchema(
    var itemCheckList: List<CheckItemDto> = ArrayList()
)
{
    fun toCommand(taskId: Long, orderId: String): CompletePrepareOrderTaskUseCase.Command
    {
        return CompletePrepareOrderTaskUseCase.Command(
            taskId = taskId,
            orderId = orderId,
            items = this.itemCheckList.map {
                Item(
                    id = it.item.id,
                    quantity = it.item.quantity,
                    deliveryDate = it.deliveryDate
                )
            }
        )
    }
}
