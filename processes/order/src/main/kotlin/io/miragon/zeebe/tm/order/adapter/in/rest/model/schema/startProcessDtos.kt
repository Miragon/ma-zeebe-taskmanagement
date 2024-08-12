package io.miragon.zeebe.tm.order.adapter.`in`.rest.model.schema

import java.math.BigDecimal

// Data to show in the UI
data class LoadItemsDto(
    val items: List<ItemDto>,
) : FormDataDto()

data class ItemDto(
    val id: String,
    val name: String,
    val price: BigDecimal,
    val image: String,
)

// Data that is sent to the application
data class PlaceOrderDto(
    val firstname: String,
    val lastname: String,
    val email: String,
    val street: String,
    val city: String,
    val zip: String,
    val items: List<PlaceOrderItemDto>,
) : FormDataDto()

data class PlaceOrderItemDto(
    val id: String,
    val quantity: Int,
)

