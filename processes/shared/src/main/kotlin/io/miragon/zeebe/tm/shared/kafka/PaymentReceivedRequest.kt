package io.miragon.zeebe.tm.shared.kafka

data class PaymentReceivedRequest(
    val invoiceId: String
)