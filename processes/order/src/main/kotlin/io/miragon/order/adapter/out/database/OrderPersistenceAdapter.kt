package io.miragon.order.adapter.out.database

import io.miragon.order.application.port.out.OrderPersistencePort
import io.miragon.order.domain.Order
import org.springframework.stereotype.Component

@Component
class OrderPersistenceAdapter(
    private val orderRepository: OrderRepository
) : OrderPersistencePort
{
    override fun findAll(): List<Order>
    {
        return orderRepository.findAll().map { orderEntity ->
            Order(
                customerName = orderEntity.customerName,
                deliveryAddress = orderEntity.deliveryAddress,
                items = orderEntity.items,
                state = Order.OrderState.valueOf(orderEntity.state)
            )
        }
    }

    override fun findById(id: Long): Order
    {
        val orderEntity = orderRepository.findById(id).orElseThrow { throw RuntimeException("Order not found") }
        return Order(
            customerName = orderEntity.customerName,
            deliveryAddress = orderEntity.deliveryAddress,
            items = orderEntity.items,
            state = Order.OrderState.valueOf(orderEntity.state)
        )
    }

    override fun save(id: Long, order: Order): Long
    {
        val orderEntity = OrderEntity(
            id = id,
            customerName = order.customerName,
            deliveryAddress = order.deliveryAddress,
            items = order.items,
            state = order.state.toString()
        )
        return orderRepository.save(orderEntity).id
    }
}
