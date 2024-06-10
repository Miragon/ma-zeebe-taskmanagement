package io.miragon.order.adapter.out.database

import io.miragon.order.application.port.out.OrderPersistencePort
import io.miragon.order.domain.Order
import org.springframework.stereotype.Component
import java.util.*

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

    override fun findById(id: String): Order
    {
        val uuid = UUID.fromString(id)
        val orderEntity = orderRepository.findById(uuid).orElseThrow { throw RuntimeException("Order not found") }
        return Order(
            customerName = orderEntity.customerName,
            deliveryAddress = orderEntity.deliveryAddress,
            items = orderEntity.items,
            state = Order.OrderState.valueOf(orderEntity.state)
        )
    }

    override fun update(id: String, order: Order): String
    {
        val res = orderRepository.save(
            OrderEntity(
                id = UUID.fromString(id),
                customerName = order.customerName,
                deliveryAddress = order.deliveryAddress,
                items = order.items,
                state = order.state.toString()
            )
        )

        return res.id.toString()
    }

    override fun save(order: Order): String
    {
        val res = orderRepository.save(
            OrderEntity(
                customerName = order.customerName,
                deliveryAddress = order.deliveryAddress,
                items = order.items,
                state = order.state.toString()
            )
        )

        return res.id.toString()
    }
}
