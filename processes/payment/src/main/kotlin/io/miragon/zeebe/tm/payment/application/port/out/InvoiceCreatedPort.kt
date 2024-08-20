package io.miragon.zeebe.tm.payment.application.port.out

interface InvoiceCreatedPort
{
    fun handle(invoiceId: String, orderId: String)
}