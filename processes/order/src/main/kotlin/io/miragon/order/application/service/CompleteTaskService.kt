package io.miragon.order.application.service

import io.miragon.order.application.port.`in`.CompleteTaskUseCase
import io.miragon.order.application.port.out.CompleteTaskPort
import org.springframework.stereotype.Service

@Service
class CompleteTaskService(private val port: CompleteTaskPort) : CompleteTaskUseCase
{
    override fun completeTask(id: String): Boolean = port.completeTask(id)
}
