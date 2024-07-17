package io.miragon.zeebe.tm.order.adapter.out.database

import io.miragon.zeebe.tm.order.application.port.out.OrderPersistencePort
import io.miragon.zeebe.tm.order.domain.Address
import io.miragon.zeebe.tm.order.domain.Item
import io.miragon.zeebe.tm.order.domain.Order
import org.springframework.stereotype.Component
import java.util.*

@Component
class OrderPersistenceAdapter(
    private val orderRepository: OrderRepository
) : OrderPersistencePort
{
    override fun findAll(): List<Order>
    {
        return orderRepository.findAll().map { toOrder(it) }
    }

    override fun findById(id: String): Order
    {
        val uuid = UUID.fromString(id)
        val orderEntity = orderRepository.findById(uuid).orElseThrow { throw RuntimeException("Order not found") }
        return toOrder(orderEntity)
    }

    override fun update(id: String, order: Order): String
    {
        val res = orderRepository.save(toOrderEntity(order, id))
        return res.id.toString()
    }

    override fun save(order: Order): String
    {
        val res = orderRepository.save(toOrderEntity(order))
        return res.id.toString()
    }

    private fun toOrder(orderEntity: OrderEntity): Order
    {
        return Order(
            customerName = orderEntity.customerName,
            deliveryAddress = orderEntity.deliveryAddress.let {
                Address(
                    street = it["street"] as String,
                    zip = it["zipCode"] as String,
                    city = it["city"] as String
                )

            },
            items = orderEntity.items.map {
                Item(
                    id = (it["id"] as Int).toLong(),
                    quantity = it["quantity"] as Int,
                )
            },
            state = Order.OrderState.valueOf(orderEntity.state)
        )
    }

    private fun toOrderEntity(order: Order, id: String? = null): OrderEntity
    {
        return OrderEntity(
            id = id?.let { UUID.fromString(it) },
            customerName = order.customerName,
            deliveryAddress = mapOf(
                "street" to order.deliveryAddress.street,
                "zipCode" to order.deliveryAddress.zip,
                "city" to order.deliveryAddress.city
            ),
            items = order.items.map {
                mapOf(
                    "id" to it.id,
                    "quantity" to it.quantity
                )
            },
            state = order.state.toString()
        )
    }
}
