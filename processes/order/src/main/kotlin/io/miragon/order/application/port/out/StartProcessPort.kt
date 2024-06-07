package io.miragon.order.application.port.out

interface StartProcessPort
{
    fun startProcess(orderId: String): Long
}