package io.miragon.zeebe.tm.order.application.port.`in`

interface CompletePrepareOrderTaskUseCase
{
    fun complete(command: Command)

    data class Command(
        val orderId: String,
        val items: List<Map<String, Any>>,
    )
}