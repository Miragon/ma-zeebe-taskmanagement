package io.miragon.zeebe.tm.order.domain

import java.time.LocalDate


data class Order(
    val firstname: String,
    val lastname: String,
    val email: String,
    val street: String,
    val city: String,
    val zip: String,
    var items: List<Item>,
    var state: State?,
    var processInstanceKey: Long?,
    var deliveryDate: LocalDate?,
    var modeOfDispatch: String?,
)
{
    enum class State
    {
        CREATED,
        CHECKED,
        DECLINED,
        PREPARED,
        PAYED,
        COMPLETE
    }
}