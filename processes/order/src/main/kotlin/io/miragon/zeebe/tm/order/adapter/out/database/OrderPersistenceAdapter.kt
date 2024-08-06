package io.miragon.zeebe.tm.order.adapter.out.database

import io.miragon.zeebe.tm.order.application.port.out.OrderPersistencePort
import io.miragon.zeebe.tm.order.domain.Item
import io.miragon.zeebe.tm.order.domain.Order
import org.springframework.stereotype.Component
import java.util.*

@Component
class OrderPersistenceAdapter(
    private val orderRepository: OrderRepository,
    private val itemRepository: ItemRepository,
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
            firstname = orderEntity.firstname,
            lastname = orderEntity.lastname,
            email = orderEntity.email,
            street = orderEntity.street,
            city = orderEntity.city,
            zip = orderEntity.zip,
            state = Order.OrderState.valueOf(orderEntity.state),
            items = orderEntity.orderItems.map {
                Item(
                    id = it.item.id.toString(),
                    name = it.item.name,
                    price = it.item.price,
                    image = it.item.image,
                    quantity = it.quantity
                )
            }
        )
    }

    private fun toOrderEntity(order: Order, id: String? = null): OrderEntity
    {
        val orderEntity = OrderEntity(
            id = id?.let { UUID.fromString(it) },
            firstname = order.firstname,
            lastname = order.lastname,
            email = order.email,
            street = order.street,
            city = order.city,
            zip = order.zip,
            deliveryDate = order.deliveryDate,
            state = order.state.name,
        )

        val orderItems = order.items.map { item ->
            val itemEntity = itemRepository
                .findById(UUID.fromString(item.id))
                .orElseThrow {
                    throw IllegalArgumentException("Item with id ${item.id} not found")
                }

            OrderItemEntity(
                order = orderEntity,
                item = itemEntity,
                quantity = item.quantity ?: throw RuntimeException("Quantity must be set"),
            )

        }

        return orderEntity.copy(orderItems = orderItems)
    }
}
