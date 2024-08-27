package io.miragon.zeebe.tm.payment.application.port.out

interface TaskManagerPort
{
    fun markTaskAsCompleted(taskId: Long): Boolean
}