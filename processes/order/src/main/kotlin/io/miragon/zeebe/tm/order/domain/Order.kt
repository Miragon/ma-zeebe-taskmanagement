package io.miragon.zeebe.tm.order.domain

import java.time.LocalDate


data class Order(
    val firstname: String,
    val lastname: String,
    val email: String,
    val street: String,
    val city: String,
    val zip: String,
    val items: List<Item>,
    var state: OrderState,
    var deliveryDate: LocalDate? = null,
)
{
    enum class OrderState
    {
        CREATED,
        CHECKED,
        DECLINED,
        PREPARED,
        PAYED,
        COMPLETE
    }
}