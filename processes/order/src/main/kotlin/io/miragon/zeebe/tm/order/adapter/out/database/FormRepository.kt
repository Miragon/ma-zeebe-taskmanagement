package io.miragon.zeebe.tm.order.adapter.out.database

import org.springframework.data.jpa.repository.JpaRepository

interface FormRepository : JpaRepository<FormEntity, FormKey>