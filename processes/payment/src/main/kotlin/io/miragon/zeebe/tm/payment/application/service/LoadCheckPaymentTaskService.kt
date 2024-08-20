package io.miragon.zeebe.tm.payment.application.service

import io.miragon.zeebe.tm.payment.application.port.`in`.LoadCheckPaymentTaskUseCase
import io.miragon.zeebe.tm.payment.application.port.`in`.LoadCheckPaymentTaskUseCase.Command
import io.miragon.zeebe.tm.payment.application.port.`in`.LoadCheckPaymentTaskUseCase.Response
import org.springframework.stereotype.Service

@Service
class LoadCheckPaymentTaskService : LoadCheckPaymentTaskUseCase
{
    override fun load(command: Command): Response
    {
        TODO("Not yet implemented")
    }
}