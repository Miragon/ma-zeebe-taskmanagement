package io.miragon.order.adapter.out.database

import jakarta.persistence.Convert
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "order")
data class OrderEntity(
    @Id
    val id: Long,

    val customerName: String,

    @Convert(converter = MapConverter::class)
    val deliveryAddress: Map<String, String>,

    @Convert(converter = MapConverter::class)
    val items: Map<String, String>,

    val state: String,
)
