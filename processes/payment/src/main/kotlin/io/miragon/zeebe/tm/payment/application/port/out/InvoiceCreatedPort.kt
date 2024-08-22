package io.miragon.zeebe.tm.payment.application.port.out

interface InvoiceCreatedPort
{
    fun publish(invoiceId: String, orderId: String)
}