package io.miragon.zeebe.tm.order.adapter.`in`.rest.model

import io.miragon.zeebe.tm.order.domain.Address
import io.miragon.zeebe.tm.order.domain.Item
import io.miragon.zeebe.tm.order.domain.Order
import io.miragon.zeebe.tm.order.domain.Order.OrderState

data class OrderSchema(

    val name: String,

    val address: AddressDto,

    val items: List<ItemDto>,
)
{
    fun toOrder(state: OrderState): Order
    {
        return Order(
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
    }
}