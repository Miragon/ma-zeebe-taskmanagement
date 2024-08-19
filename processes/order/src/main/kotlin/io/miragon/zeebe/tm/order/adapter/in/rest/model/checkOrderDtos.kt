package io.miragon.zeebe.tm.order.adapter.`in`.rest.model

import java.math.BigDecimal

// Data to show in the UI
data class LoadOrderDto(
    val firstname: String,
    val lastname: String,
    val email: String,
    val street: String,
    val city: String,
    val zip: String,
    val items: List<CheckOrderItemDto>,
) : FormDataDto()

data class CheckOrderItemDto(
    val id: String,
    val name: String,
    val price: BigDecimal,
    val image: String,
    val quantity: Int,
)

// Data that is sent to the application
data class CheckOrderDto(
    val isOrderValid: Boolean,
) : FormDataDto()
