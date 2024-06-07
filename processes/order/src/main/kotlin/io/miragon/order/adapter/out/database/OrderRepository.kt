package io.miragon.order.adapter.out.database

import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface OrderRepository : JpaRepository<OrderEntity, UUID>