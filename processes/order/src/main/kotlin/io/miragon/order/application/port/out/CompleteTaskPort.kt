package io.miragon.order.application.port.out

interface CompleteTaskPort
{
    fun completeCheckOrderTask(id: Long, approved: Boolean): Boolean

    fun completePrepareOrderTask(id: Long): Boolean
}
