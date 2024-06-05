package io.miragon.order.domain


data class Order(
    val customerName: String,
    val deliveryAddress: Map<String, String>,
    val items: Map<String, String>,
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