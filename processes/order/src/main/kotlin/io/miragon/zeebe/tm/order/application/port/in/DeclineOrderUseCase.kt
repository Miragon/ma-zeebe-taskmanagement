package io.miragon.zeebe.tm.order.application.port.`in`

interface DeclineOrderUseCase
{
    /**
     * Declines the order.
     * @return True if the order was declined, false otherwise.
     */
    fun decline(command: Command)

    data class Command(
        val orderId: String,
    )
}