package io.miragon.order.application.port.out

import io.miragon.order.domain.Order

interface OrderPersistencePort
{
    fun findAll(): List<Order>

    fun findById(id: String): Order

    fun update(id: String, order: Order): String

    fun save(order: Order): String
}