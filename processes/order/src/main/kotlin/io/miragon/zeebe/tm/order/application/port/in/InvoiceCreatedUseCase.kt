package io.miragon.zeebe.tm.order.application.port.`in`

interface InvoiceCreatedUseCase
{
    fun handle(command: Command)

    data class Command(
        val invoiceId: String,
        val orderId: String,
    )
}