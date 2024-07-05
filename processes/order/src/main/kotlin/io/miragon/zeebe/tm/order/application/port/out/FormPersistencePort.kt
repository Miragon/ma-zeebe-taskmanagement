package io.miragon.zeebe.tm.order.application.port.out

import io.miragon.zeebe.tm.order.domain.Form

interface FormPersistencePort
{
    fun readProcessStartForm(): Form.JsonForm

    fun readCheckOrderForm(): Form.JsonForm

    fun readPrepareOrderForm(): Form.JsonForm
}