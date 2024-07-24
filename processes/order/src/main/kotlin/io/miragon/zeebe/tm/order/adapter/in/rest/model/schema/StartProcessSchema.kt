package io.miragon.zeebe.tm.order.adapter.`in`.rest.model.schema

data class StartProcessSchema(

    val name: String,

    val address: AddressDto,

    val items: List<ItemDto>,
)
