package io.miragon.order.application.port.out

interface CompleteTaskPort
{
    fun completeTask(id: String): Boolean
}
