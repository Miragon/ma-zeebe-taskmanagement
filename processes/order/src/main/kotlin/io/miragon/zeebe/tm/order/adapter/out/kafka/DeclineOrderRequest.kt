package io.miragon.zeebe.tm.order.adapter.out.kafka

data class DeclineOrderRequest(
    val orderId: String,
)