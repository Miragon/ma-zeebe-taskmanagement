package io.miragon.zeebe.tm.payment.application.port.`in`

import io.miragon.zeebe.tm.payment.domain.Form
import io.miragon.zeebe.tm.payment.domain.Invoice

interface LoadCheckPaymentTaskUseCase
{
    /**
     * Loads the check payment task.
     * @return The id of the loaded task.
     */
    fun load(command: Command): Response

    data class Command(
        val invoiceId: String,
        val filePath: String,
    )

    data class Response(
        val form: Form.JsonForm,
        val invoice: Invoice,
    )
}