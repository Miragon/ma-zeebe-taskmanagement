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
    private val orderItemRepository: OrderItemRepository,
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
        val orderEntity = toOrderEntity(order, id)
        val orderItemEntity = toOrderItemEntity(orderEntity, order.items)
        val res = orderRepository.save(orderEntity.copy(orderItems = orderItemEntity))
        return res.id.toString()
    }

    override fun save(order: Order): String
    {
        val orderEntity = toOrderEntity(order)
        val orderSaved = orderRepository.save(orderEntity)
        val orderItemsEntity = toOrderItemEntity(orderSaved, order.items)
        orderItemRepository.saveAll(orderItemsEntity)

        return orderSaved.id.toString()
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
            state = Order.State.valueOf(orderEntity.state),
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

    private fun toOrderEntity(order: Order, id: String? = null, orderItems: List<OrderItemEntity>? = null): OrderEntity
    {
        return OrderEntity(
            id = id?.let { UUID.fromString(it) },
            firstname = order.firstname,
            lastname = order.lastname,
            email = order.email,
            street = order.street,
            city = order.city,
            zip = order.zip,
            deliveryDate = order.deliveryDate,
            state = order.state?.name ?: throw IllegalArgumentException("State must be set"),
            orderItems = orderItems ?: emptyList()
        )
    }

    private fun toOrderItemEntity(orderEntity: OrderEntity, orderItems: List<Item>): List<OrderItemEntity>
    {
        return orderItems.map { item ->
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
    }
}
