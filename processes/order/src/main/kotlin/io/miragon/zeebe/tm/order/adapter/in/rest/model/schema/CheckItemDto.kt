package io.miragon.zeebe.tm.order.adapter.`in`.rest.model.schema

data class CheckItemDto(
    val item: ItemDto,

    val isAvailable: Boolean,

    val deliveryDate: String,
)
