package io.miragon.zeebe.tm.payment.application.port.`in`

import java.math.BigDecimal

interface CreateInvoiceUseCase
{
    fun create(command: Command): Response

    data class Command(
        val orderId: String,
        val amount: BigDecimal,
    )

    data class Response(
        val invoiceId: String,
        val processInstanceKey: Long,
    )
}