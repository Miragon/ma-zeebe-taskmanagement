package io.miragon.zeebe.tm.order.adapter.out.database

import jakarta.persistence.*
import java.time.LocalDate
import java.util.*

@Entity
@Table(name = "orders")
class OrderEntity(
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

    @OneToMany(mappedBy = "order", cascade = [CascadeType.ALL], orphanRemoval = true, fetch = FetchType.EAGER)
    val orderItems: List<OrderItemEntity> = mutableListOf(),
)
{
    fun copy(
        firstname: String = this.firstname,
        lastname: String = this.lastname,
        email: String = this.email,
        street: String = this.street,
        city: String = this.city,
        zip: String = this.zip,
        deliveryDate: LocalDate? = this.deliveryDate,
        state: String = this.state,
        orderItems: List<OrderItemEntity> = this.orderItems,
    ): OrderEntity
    {
        return OrderEntity(
            id = this.id,
            firstname = firstname,
            lastname = lastname,
            email = email,
            street = street,
            city = city,
            zip = zip,
            deliveryDate = deliveryDate,
            state = state,
            orderItems = orderItems,
        )
    }
}