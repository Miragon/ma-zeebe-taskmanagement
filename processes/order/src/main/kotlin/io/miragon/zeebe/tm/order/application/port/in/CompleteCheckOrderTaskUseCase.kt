package io.miragon.zeebe.tm.order.application.port.`in`

interface CompleteCheckOrderTaskUseCase
{
    fun complete(command: Command)

    data class Command(
        val orderId: String,
        val isAccepted: Boolean,
    )
}
