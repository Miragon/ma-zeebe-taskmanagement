package io.miragon.zeebe.tm.order.adapter.`in`.rest.model.schema

data class CheckOrderDto(
    val firstname: String,
    val lastname: String,
    val email: String,
    val street: String,
    val city: String,
    val zip: String,
    val items: List<ItemDto>,
    val isOrderValid: Boolean? = null,
)