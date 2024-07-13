package io.miragon.zeebe.tm.order.application.port.out

import io.miragon.zeebe.tm.order.domain.Form

interface FormPersistencePort
{
    fun readProcessStartForm(path: String): Form.JsonForm

    fun readCheckOrderForm(path: String): Form.JsonForm

    fun readPrepareOrderForm(path: String): Form.JsonForm
}