package io.miragon.zeebe.tm.order.application.port.out

interface DeclineOrderPort
{
    /**
     * Declines the order.
     * @return True if the order was declined, false otherwise.
     */
    fun publish(orderId: String): Boolean
}