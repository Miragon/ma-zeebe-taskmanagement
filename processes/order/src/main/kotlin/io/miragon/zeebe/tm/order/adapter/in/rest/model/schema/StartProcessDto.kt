package io.miragon.zeebe.tm.order.adapter.`in`.rest.model.schema

data class StartProcessResponseDto(
    val items: List<ItemDto>,
)

data class StartProcessResultDto(
    val firstname: String,
    val lastname: String,
    val email: String,
    val street: String,
    val city: String,
    val zip: String,
    val items: List<ItemDto>,
)