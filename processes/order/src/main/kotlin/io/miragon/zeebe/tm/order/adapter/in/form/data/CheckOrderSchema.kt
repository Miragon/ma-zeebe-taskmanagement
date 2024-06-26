package io.miragon.zeebe.tm.order.adapter.`in`.form.data

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class CheckOrderSchema(
    @get:NotBlank(message = "Name must not be blank")
    val customerName: String,

    val deliveryAddress: AddressDto,

    val items: List<ItemDto> = ArrayList(),

    @get:NotNull
    val isOrderValid: Boolean
)

