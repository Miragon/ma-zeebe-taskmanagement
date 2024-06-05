package io.miragon.order.adapter.out.database

import org.springframework.data.jpa.repository.JpaRepository

interface FormRepository : JpaRepository<FormEntity, String>