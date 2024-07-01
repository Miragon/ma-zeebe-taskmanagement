package io.miragon.zeebe.tm.order.adapter.`in`.rest.model

import jakarta.validation.constraints.Size

data class AddressDto(
    val street: String,

    @get:Size(min = 5, max = 5, message = "Zip code must be 5 characters long")
    val zipCode: String,

    val city: String
)