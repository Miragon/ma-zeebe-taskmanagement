package io.miragon.order.application.service

import io.miragon.order.application.port.`in`.StartProcessUseCase
import io.miragon.order.application.port.out.StartProcessPort
import org.springframework.stereotype.Service

@Service
class StartProcessService(private val port: StartProcessPort) : StartProcessUseCase
{
    override fun startProcess() = port.startProcess()
}
