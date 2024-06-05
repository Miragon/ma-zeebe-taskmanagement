package io.miragon.order.application.port.out

import io.miragon.order.domain.Form

interface FormPersistencePort
{
    fun load(id: String): Form

    fun save(form: Form)
}