package io.miragon.zeebe.tm.order.adapter.`in`.form.data

data class CheckItemDto(
    val item: ItemDto,

    val isAvailable: Boolean,

    val deliveryDate: String,
)