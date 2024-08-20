package io.miragon.zeebe.tm.payment.application.service

import io.miragon.zeebe.tm.payment.application.port.`in`.LoadCheckPaymentTaskUseCase
import io.miragon.zeebe.tm.payment.application.port.`in`.LoadCheckPaymentTaskUseCase.Command
import io.miragon.zeebe.tm.payment.application.port.`in`.LoadCheckPaymentTaskUseCase.Response
import io.miragon.zeebe.tm.payment.application.port.out.FormPersistencePort
import io.miragon.zeebe.tm.payment.application.port.out.InvoicePersistencePort
import org.springframework.stereotype.Service

@Service
class LoadCheckPaymentTaskService(
    private val formPersistencePort: FormPersistencePort,
    private val invoicePersistencePort: InvoicePersistencePort,
) : LoadCheckPaymentTaskUseCase
{
    override fun load(command: Command): Response
    {
        val (invoiceId, filePath) = command

        val form = formPersistencePort.readCheckPaymentForm(filePath)
        val invoice = invoicePersistencePort.findById(invoiceId)

        return Response(
            form = form,
            invoice = invoice,
        )
    }
}