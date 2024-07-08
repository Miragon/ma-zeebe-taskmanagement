package io.miragon.zeebe.tm.order.adapter.`in`.rest.model

import io.miragon.zeebe.tm.order.domain.Item

data class CheckItemDto(
    val item: ItemDto,

    val isAvailable: Boolean,

    val deliveryDate: String,
)
{
    fun toItem(): Item
    {
        return Item(
            id = this.item.id,
            quantity = this.item.quantity,
            deliveryDate = this.deliveryDate
        )
    }
}
