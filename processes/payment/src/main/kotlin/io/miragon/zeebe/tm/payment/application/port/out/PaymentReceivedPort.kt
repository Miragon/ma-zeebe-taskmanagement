package io.miragon.zeebe.tm.payment.application.port.out

interface PaymentReceivedPort
{
    fun handle(invoiceId: String)
}