package io.miragon.zeebe.tm.order.adapter.`in`.rest.model.schema

import java.math.BigDecimal

data class ItemDto(
    val id: String,
    val name: String? = null,
    val price: BigDecimal? = null,
    val image: String? = null,
    val quantity: Int? = null,
)