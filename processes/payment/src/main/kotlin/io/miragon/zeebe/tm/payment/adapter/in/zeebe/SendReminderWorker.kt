package io.miragon.zeebe.tm.payment.adapter.`in`.zeebe

import io.camunda.zeebe.spring.client.annotation.JobWorker
import io.miragon.zeebe.tm.payment.application.port.`in`.SendReminderUseCase
import org.springframework.stereotype.Component

@Component
class SendReminderWorker(
    private val useCase: SendReminderUseCase
)
{
    @JobWorker(type = "sendReminder")
    fun sendReminder()
    {
        useCase.handle()
    }
}