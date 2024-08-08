package io.miragon.zeebe.tm.order.adapter.`in`.rest.model

import io.miragon.zeebe.tm.order.adapter.`in`.rest.model.schema.CheckOrderDto
import io.miragon.zeebe.tm.order.adapter.`in`.rest.model.schema.PlaceOrderDto
import io.miragon.zeebe.tm.order.adapter.`in`.rest.model.schema.PrepareOrderSchema
import io.miragon.zeebe.tm.order.application.port.`in`.CompleteCheckOrderTaskUseCase
import io.miragon.zeebe.tm.order.application.port.`in`.CompletePrepareOrderTaskUseCase
import io.miragon.zeebe.tm.order.application.port.`in`.StartProcessUseCase
import io.miragon.zeebe.tm.order.domain.Item
import io.miragon.zeebe.tm.order.domain.Order
import io.miragon.zeebe.tm.order.domain.Order.OrderState

fun PlaceOrderDto.toCommand(state: OrderState) = StartProcessUseCase.Command(
    order = Order(
        firstname = this.firstname,
        lastname = this.lastname,
        email = this.email,
        street = this.street,
        city = this.city,
        zip = this.zip,
        state = state,
        items = this.items.map {
            Item(
                id = it.id,
                quantity = it.quantity ?: throw IllegalArgumentException("Item quantity must not be null")
            )
        }
    )
)

fun CheckOrderDto.toCommand(taskId: Long, orderId: String): CompleteCheckOrderTaskUseCase.Command
{
    return CompleteCheckOrderTaskUseCase.Command(
        taskId = taskId,
        orderId = orderId,
        isAccepted = this.isOrderValid ?: false
    )
}

fun PrepareOrderSchema.toCommand(
    taskId: Long,
    orderId: String,
): CompletePrepareOrderTaskUseCase.Command
{
    return CompletePrepareOrderTaskUseCase.Command(
        taskId = taskId,
        orderId = orderId,
        deliverDate = this.deliveryDate
    )
}
