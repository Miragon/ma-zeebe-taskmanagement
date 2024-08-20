package io.miragon.zeebe.tm.payment.adapter.`in`.kafka

import java.math.BigDecimal

data class PaymentRequest(
    val orderId: String,
    val amount: BigDecimal,
)