package io.miragon.zeebe.tm.order.application.port.out

interface TaskManagerPort
{
    fun markTaskAsCompleted(taskId: Long): Boolean
}