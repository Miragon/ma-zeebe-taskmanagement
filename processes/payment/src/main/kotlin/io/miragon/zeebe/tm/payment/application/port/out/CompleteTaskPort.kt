package io.miragon.zeebe.tm.payment.application.port.out

interface CompleteTaskPort
{
    fun completeCheckPaymentTask(taskId: Long, approved: Boolean)
}