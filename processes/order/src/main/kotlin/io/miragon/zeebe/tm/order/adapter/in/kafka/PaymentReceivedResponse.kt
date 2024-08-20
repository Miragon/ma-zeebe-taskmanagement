package io.miragon.zeebe.tm.order.adapter.`in`.kafka

data class PaymentReceivedResponse(
    val invoiceId: String
)