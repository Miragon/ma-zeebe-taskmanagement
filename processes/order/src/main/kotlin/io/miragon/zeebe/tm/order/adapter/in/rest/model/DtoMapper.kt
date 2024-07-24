package io.miragon.zeebe.tm.order.adapter.`in`.rest.model

import io.miragon.zeebe.tm.order.adapter.`in`.rest.model.schema.CheckOrderSchema
import io.miragon.zeebe.tm.order.adapter.`in`.rest.model.schema.PrepareOrderSchema
import io.miragon.zeebe.tm.order.adapter.`in`.rest.model.schema.StartProcessSchema
import io.miragon.zeebe.tm.order.application.port.`in`.CompleteCheckOrderTaskUseCase
import io.miragon.zeebe.tm.order.application.port.`in`.CompletePrepareOrderTaskUseCase
import io.miragon.zeebe.tm.order.application.port.`in`.StartProcessUseCase
import io.miragon.zeebe.tm.order.domain.Address
import io.miragon.zeebe.tm.order.domain.Item
import io.miragon.zeebe.tm.order.domain.Order
import io.miragon.zeebe.tm.order.domain.Order.OrderState

fun StartProcessSchema.toCommand(state: OrderState) = StartProcessUseCase.Command(
    order = Order(
        customerName = this.name,
        deliveryAddress = this.address.let {
            Address(
                street = it.street,
                city = it.city,
                zip = it.zipCode
            )
        },
        items = this.items.map {
            Item(
                id = it.id,
                quantity = it.quantity
            )
        },
        state = state
    )
)

fun CheckOrderSchema.toCommand(taskId: Long, orderId: String): CompleteCheckOrderTaskUseCase.Command
{
    return CompleteCheckOrderTaskUseCase.Command(
        taskId = taskId,
        orderId = orderId,
        isAccepted = this.isOrderValid
    )
}

fun PrepareOrderSchema.toCommand(taskId: Long, orderId: String): CompletePrepareOrderTaskUseCase.Command
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
