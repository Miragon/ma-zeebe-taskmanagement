package io.miragon.zeebe.tm.order.application.port.out

interface SetInvoiceIdPort
{
    fun setInvoiceId(processInstanceKey: Long, invoiceId: String)
}