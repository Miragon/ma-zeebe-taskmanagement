package io.miragon.zeebe.tm.payment.adapter.out.kafka

data class PaymentReceivedRequest(
    val invoiceId: String
)