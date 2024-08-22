package io.miragon.zeebe.tm.shared.kafka

data class InvoiceIdRequest(
    val invoiceId: String,
    val orderId: String,
)