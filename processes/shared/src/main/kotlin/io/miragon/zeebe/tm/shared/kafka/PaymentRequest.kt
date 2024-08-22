package io.miragon.zeebe.tm.shared.kafka

import java.math.BigDecimal

data class PaymentRequest(
    val orderId: String,
    val amount: BigDecimal,
)
