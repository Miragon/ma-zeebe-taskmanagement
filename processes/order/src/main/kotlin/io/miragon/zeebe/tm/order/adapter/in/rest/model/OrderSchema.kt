package io.miragon.zeebe.tm.order.adapter.`in`.rest.model

data class OrderSchema(

    val name: String,

    val address: AddressDto,

    val items: List<ItemDto>,
)