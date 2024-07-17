package io.miragon.zeebe.tm.order.adapter.`in`.rest.model.schema

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class AddressDto(
    @get:NotBlank
    val street: String,

    @get:NotBlank
    @get:Size(min = 5, max = 5, message = "Zip code must be 5 characters long")
    val zipCode: String,

    @get:NotBlank
    val city: String
)