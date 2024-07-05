package io.miragon.zeebe.tm.order.domain


data class Order(
    val customerName: String,
    val deliveryAddress: Address,
    var items: List<Item>,
    var state: OrderState,
)
{
    enum class OrderState
    {
        CHECK,
        PREPARE,
        DELIVER,
        COMPLETE
    }
}