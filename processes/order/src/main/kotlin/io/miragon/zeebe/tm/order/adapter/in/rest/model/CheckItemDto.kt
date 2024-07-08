package io.miragon.zeebe.tm.order.adapter.`in`.rest.model

data class CheckItemDto(
    val item: ItemDto,

    val isAvailable: Boolean,

    val deliveryDate: String,
)
