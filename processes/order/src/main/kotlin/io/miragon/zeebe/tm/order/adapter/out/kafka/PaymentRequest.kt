package io.miragon.zeebe.tm.order.adapter.out.kafka

import java.math.BigDecimal

data class PaymentRequest(
    val orderId: String,
    val amount: BigDecimal,
)