package io.miragon.zeebe.tm.order.domain


data class Order(
    val customerName: String,
    val deliveryAddress: Map<String, Any>,
    var items: List<Map<String, Any>>,
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