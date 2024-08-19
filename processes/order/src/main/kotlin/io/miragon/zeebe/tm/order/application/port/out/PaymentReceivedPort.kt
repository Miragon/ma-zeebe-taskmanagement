package io.miragon.zeebe.tm.order.application.port.out

interface PaymentReceivedPort
{
    fun correlateMessage(paymentId: String)
}