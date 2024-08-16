package io.miragon.zeebe.tm.order.application.port.out

interface CompleteTaskPort
{
    fun completeCheckOrderTask(id: Long, approved: Boolean): Boolean

    fun completePrepareDeliveryTask(id: Long): Boolean
}
