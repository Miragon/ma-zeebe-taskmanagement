package io.miragon.zeebe.tm.payment.adapter.out.kafka

data class InvoiceIdRequest(
    val invoiceId: String,
    val orderId: String,
)