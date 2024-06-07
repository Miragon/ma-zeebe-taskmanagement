package io.miragon.order.application.port.out

import io.miragon.order.domain.Order

interface OrderPersistencePort
{
    fun findAll(): List<Order>

    fun findById(id: String): Order

    fun save(order: Order): String
}