package io.miragon.zeebe.tm.order.adapter.`in`.kafka

data class InvoiceIdResponse(
    val invoiceId: String,
    val orderId: String,
)