package io.miragon.zeebe.tm.order.adapter.out.database

import jakarta.persistence.*
import java.time.LocalDate
import java.util.*

@Entity
@Table(name = "orders")
data class OrderEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID? = null,

    @Column(nullable = false)
    val firstname: String,

    @Column(nullable = false)
    val lastname: String,

    @Column(nullable = false)
    val email: String,

    @Column(nullable = false)
    val street: String,

    @Column(nullable = false)
    val city: String,

    @Column(nullable = false)
    val zip: String,

    @Column(nullable = true)
    val deliveryDate: LocalDate? = null,

    @Column(nullable = false)
    val state: String,

    @OneToMany(mappedBy = "order", cascade = [CascadeType.ALL], orphanRemoval = true)
    val orderItems: List<OrderItemEntity> = mutableListOf(),
)