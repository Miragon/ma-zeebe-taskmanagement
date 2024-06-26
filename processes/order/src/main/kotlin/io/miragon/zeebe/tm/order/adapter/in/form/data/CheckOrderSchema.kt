package io.miragon.zeebe.tm.order.adapter.`in`.form.data

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.NotBlank
import org.jetbrains.annotations.NotNull

data class CheckOrderSchema(
    @get:JsonProperty("customer_name")
    @get:NotBlank(message = "Name must not be blank")
    val customerName: String,

    val deliveryAddress: AddressDto,

    val items: List<ItemDto> = ArrayList(),

    @get:NotNull
    val isOrderValid: Boolean
)

