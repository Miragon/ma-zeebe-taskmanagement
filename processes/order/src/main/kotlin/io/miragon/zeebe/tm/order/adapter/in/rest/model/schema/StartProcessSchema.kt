package io.miragon.zeebe.tm.order.adapter.`in`.rest.model.schema

import io.miragon.zeebe.tm.order.application.port.`in`.StartProcessUseCase
import io.miragon.zeebe.tm.order.domain.Address
import io.miragon.zeebe.tm.order.domain.Item
import io.miragon.zeebe.tm.order.domain.Order
import io.miragon.zeebe.tm.order.domain.Order.OrderState

data class StartProcessSchema(

    val name: String,

    val address: AddressDto,

    val items: List<ItemDto>,
)

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