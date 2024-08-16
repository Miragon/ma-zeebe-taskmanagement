package io.miragon.zeebe.tm.order.adapter.`in`.rest.model

import io.miragon.zeebe.tm.order.adapter.`in`.rest.model.schema.CheckOrderDto
import io.miragon.zeebe.tm.order.adapter.`in`.rest.model.schema.PlaceOrderDto
import io.miragon.zeebe.tm.order.adapter.`in`.rest.model.schema.PrepareDeliverySchema
import io.miragon.zeebe.tm.order.application.port.`in`.CompleteCheckOrderTaskUseCase
import io.miragon.zeebe.tm.order.application.port.`in`.CompletePrepareDeliveryTaskUseCase
import io.miragon.zeebe.tm.order.application.port.`in`.StartProcessUseCase
import io.miragon.zeebe.tm.order.domain.Item
import io.miragon.zeebe.tm.order.domain.Order

fun PlaceOrderDto.toCommand() = StartProcessUseCase.Command(
    order = Order(
        firstname = this.firstname,
        lastname = this.lastname,
        email = this.email,
        street = this.street,
        city = this.city,
        zip = this.zip,
        items = this.items.map {
            Item(
                id = it.id,
                quantity = it.quantity
            )
        }
    )
)

fun CheckOrderDto.toCommand(taskId: Long, orderId: String): CompleteCheckOrderTaskUseCase.Command
{
    return CompleteCheckOrderTaskUseCase.Command(
        taskId = taskId,
        orderId = orderId,
        isAccepted = this.isOrderValid
    )
}

fun PrepareDeliverySchema.toCommand(
    taskId: Long,
    orderId: String,
): CompletePrepareDeliveryTaskUseCase.Command
{
    return CompletePrepareDeliveryTaskUseCase.Command(
        taskId = taskId,
        orderId = orderId,
        deliveryDate = this.deliveryDate,
        modeOfDispatch = this.modeOfDispatch.name,
        items = this.itemCheckList.map {
            Item(
                id = it.id,
                quantity = it.quantity,
                ready = it.ready,
            )
        }
    )
}
