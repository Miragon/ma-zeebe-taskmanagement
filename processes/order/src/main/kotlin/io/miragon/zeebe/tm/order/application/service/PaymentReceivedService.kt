package io.miragon.zeebe.tm.order.application.service

import io.miragon.zeebe.tm.order.application.port.`in`.PaymentReceivedUseCase
import io.miragon.zeebe.tm.order.application.port.`in`.PaymentReceivedUseCase.Query
import io.miragon.zeebe.tm.order.application.port.out.PaymentReceivedPort
import org.springframework.stereotype.Service

@Service
class PaymentReceivedService(
    private val paymentReceivedPort: PaymentReceivedPort
) : PaymentReceivedUseCase
{
    override fun handle(query: Query)
    {
        val paymentId = query.paymentId
        paymentReceivedPort.correlateMessage(paymentId)
    }
}