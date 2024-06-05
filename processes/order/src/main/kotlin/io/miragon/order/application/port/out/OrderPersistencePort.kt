package io.miragon.order.application.port.out

import io.miragon.order.domain.Order

interface OrderPersistencePort
{
    fun findAll(): List<Order>

    fun findById(id: Long): Order

    fun save(id: Long, order: Order): Long
}